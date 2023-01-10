package com.example.k_register

import android.R.attr
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.k_register.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.OutputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val genderArray = arrayOf("male", "female")

    private val CAMERA_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 10

    lateinit var imageUri:String


    private lateinit var binding: ActivityMainBinding
    lateinit var appdb: AppDatabase

    private val fmailcon: TextInputEditText? = null
    private val email: TextInputEditText? = null
    private val phone: TextInputEditText? = null
    private val setpass: TextInputEditText? = null
    private val cnf: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)



        val checkbox = findViewById<CheckBox>(R.id.checkbox786)

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        cnfFocusListner()
        firstnameListner()




        binding.Saveit.setOnClickListener(View.OnClickListener { saveIntent() })
// this will call thw onselect dialog
        binding.imageUri1.setOnClickListener(View.OnClickListener { onSelectImage() })

        // this will call the next activty
        binding.texviewLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, K_register22::class.java) // start your next activity
            startActivity(intent)
        })

        binding.Fecth.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,showdat_user::class.java)
            startActivity(intent)
        })

// this will call the write data method
        binding.submit89.setOnClickListener(View.OnClickListener {
            writedata()
            if (binding.email11.text.toString().equals("")) {
                binding.email11.setError("Field is emplty")
            } else {
                val intent = Intent(this, K_register22::class.java)
                // start your next activity
                startActivity(intent)
            }

            if (binding.firstname12.text.toString().equals("")) {
                binding.firstname12.setError("Field is empty")
            } else {
                val intent = Intent(this, K_register22::class.java) // start your next activity
                startActivity(intent)

            }
            if (binding.phoneNumber11.text.toString().equals("")) {
                binding.phoneNumber11.setError("Field is empty")

            } else {
                val intent = Intent(this, K_register22::class.java) // start your next activity
                startActivity(intent)
            }
            if (binding.firstname12.text.toString().equals("")) {
                binding.firstname12.setError("Field is empty")
            } else {
                val intent = Intent(this, K_register22::class.java) // start your next activity
                startActivity(intent)

            }
            if (binding.password23.text.toString().equals("")) {
                binding.password23.setError("Field is empty")

            } else {
                val intent = Intent(this, K_register22::class.java) // start your next activity
                startActivity(intent)

            }
            if (binding.confPass56.text.toString().equals("")) {
                binding.confPass56.setError("Field is empty")

            } else {
                val intent = Intent(this, K_register22::class.java) // start your next activity
                startActivity(intent)

            }

        })

        val arrayAdapter = ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            genderArray
        )
    }

    // dialog
    private fun onSelectImage() {

        val items = arrayOf(
            "Take photo", "Select From Gallery",
            "Cancel", "save"
        )

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Add photo!!")
            builder.setItems(items) { dialog, item ->
            if (items[item] == "Take photo") {
                Log.d(TAG, "ICCHAIGO:prepamk ")
                // this will call camera method
                cameraIntent()
            } else if (items[item] == "Select From Gallery") {
                // this will call gallery method
                galleryIntent()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()


            } else if (items[item] == "save to gallery") {


            }
        }
        builder.show()


    }

    private fun saveIntent() {

        val bitmapDrawable = binding.imageUri1.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        val fos: OutputStream
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            {
              //  The ContentResolver class is a content provider client, and is used to
                //  communicate with content providers. A content provider is an Android component
                //  that manages a shared set of data and makes it available to all applications.
                //  The ContentResolver class allows you to query the data stored by content providers,
                //  and also to modify the data if you have the necessary permissions.
                val resolver = contentResolver
                val contentValues = ContentValues()

              //  MediaStore.MediaColumns.DISPLAY_NAME: This value is the display name of the image file.
                //  It will be used as the file name when the image is saved. In this case,
                //  the display name is set to "Image_.jpg".
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + ".jpg")
               /// MediaStore.MediaColumns.MIME_TYPE: This value is the MIME type of the image file.
                // It specifies the type of the file and helps the system determine how to handle the file.
                // In this case, the MIME type is set to "image/jpeg", indicating that the file is a JPEG image.
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                contentValues.put(

                 // The code concatenates the Environment.DIRECTORY_PICTURES constant and the string
                    // "TestFolder" using the File.separator string and the + operator, to create the path
                    // Environment.DIRECTORY_PICTURES/TestFolder.


                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder"
                )
                val imageUri =
                   // A ContentValues object named contentValues is inserted into
                    // the MediaStore using the insert method of the ContentResolver
                    // object resolver. The MediaStore is a store for media files, such as audio and video,
                    // and is part of the Android framework. The EXTERNAL_CONTENT_URI constant specifies
                    // that the media file is stored on the device's external storage.
              //  An OutputStream named fos is created from the imageUri using the openOutputStream method of
                    //  the ContentResolver object resolver. The OutputStream is used to write data
                    //  to the file at the specified imageUri.

                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = Objects.requireNonNull(imageUri)?.let { resolver.openOutputStream(it) }!!
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                Objects.requireNonNull(fos)
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Image not saved \n$e", Toast.LENGTH_SHORT).show()
        }


    }

    private fun galleryIntent() {

        val iGallery = Intent(Intent.ACTION_PICK)
        iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(iGallery, GALLERY_REQUEST_CODE)

    }
    private fun cameraIntent() {
        val intentcamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val imageuri=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues()
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri)
        startActivityForResult(intentcamera,CAMERA_REQUEST_CODE)
        try {
            startActivityForResult(intentcamera, CAMERA_REQUEST_CODE)
        } catch (e:AccessDeniedException){

        }

    }
// this method is for  writing the data in Room_DB
    @SuppressLint("SuspiciousIndentation")
    private fun writedata() {

        val email=binding.email11.text.toString()
        val firstname=binding.firstname12.text.toString()
        val phone=binding.phoneNumber11.text.toString()
        val setpassword=binding.password23.text.toString()
        val radio=binding.radioGrp.text.toString()




    appdb=AppDatabase.getDatabase(this)
        if (email.isNotEmpty() && firstname.isNotEmpty() && phone.isNotEmpty() && setpassword.isNotEmpty()){
            val userModel=UserModel(
                email,firstname, phone,null,setpassword,imageUri
            // i have to change null variable from here and pass here a image uri
            )
            // this is co-routine
            GlobalScope.launch(Dispatchers.IO){
              //  appdb.registerDao.insertAll(UserModel(email,firstname,phone,null,setpassword))
                // i have to pass parameter image uri here



                appdb.registerDao().insertAll(UserModel(email,firstname,phone,radio,setpassword,imageUri))
            }
            binding.email11.text?.clear()
            binding.firstname12.text.clear()
            binding.password23.text.clear()
            binding.phoneNumber11.text.clear()
            binding.confPass56.text.clear()


            Toast.makeText(this@MainActivity, "Added sucessfully", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
        }
    }



    // this method will will check the input edit text layout
    private fun firstnameListner() {
        binding.firstname12.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.containerFirstname.helperText=entFirstname()
            }
        }
    }

    private fun entFirstname(): String? {

        val firstname=binding.firstname12.text.toString()
        if (firstname.equals("")){
            return "enter your name"
        }
        return null
    }

    private fun cnfFocusListner() {
        binding.confPass56.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.cnpasswordEdit.helperText=validConf()


            }
        }
    }

    private fun validConf(): String? {
        val cnfText=binding.confPass56.text.toString()
        if (cnfText.length<8)
        {
            return "minimum 8 character required"
        }
        if(!cnfText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!cnfText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!cnfText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        return null

    }

    private fun phoneFocusListener() {
        binding.phoneNumber11.setOnFocusChangeListener { view, focused ->
            if (!focused){
                binding.number.helperText=validPhone()
            }
        }
    }

    private fun validPhone(): String? {

        val phoneText = binding.phoneNumber11.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null

    }

    private fun passwordFocusListener() {
        binding.password23.setOnFocusChangeListener{View,focused ->

            if (!focused){
                binding.txtInputPass.helperText = validPassword()
            }
        }
    }
    private fun validPassword(): String? {

        val passwordText = binding.password23.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        return null

    }

    private fun emailFocusListener() {
        binding.email11.setOnFocusChangeListener { View, focused ->

            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }
    private fun validEmail(): String? {
        val email = binding.email11.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

// we have to do 

   

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {

              //  val path = data?.data
//                // for camera
                val image: Bitmap? = data?.extras?.get("data") as Bitmap?
             binding.imageUri1.setImageBitmap(image)

                // this is to take image path from gallery but its not working now
                imageUri = data?.data.toString()
                Log.d(TAG, "hemrajuuu:SHUBHAM"+image)
               // val imageuri= data?.getData();
            } else if (requestCode == GALLERY_REQUEST_CODE) {
                binding.imageUri1.setImageURI(data!!.data)

                //getting imagepath from gallery to save in room db
                 imageUri = data?.data.toString()
                Log.d(TAG, "hemrajuuu:SHUBHAM"+data)
                // from  gallery show result
            }
        }
    }

}


