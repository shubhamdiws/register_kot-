package com.example.k_register

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.k_register.databinding.ActivityKregister22Binding
import com.example.k_register.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class K_register22 : AppCompatActivity() {

    lateinit var appdb: AppDatabase


    private lateinit var binding: ActivityKregister22Binding

    private var lgemail: TextInputEditText? = null
    private val lgpass: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKregister22Binding.inflate(layoutInflater)
        setContentView(binding.root)


        appdb = AppDatabase.getDatabase(this)

       // var em = binding.loginEmail.text.toString()
      //  var pa = binding.loginPassword.text.toString()






        binding.Loginsubmit.setOnClickListener(View.OnClickListener {
            var em = binding.loginEmail.text.toString()
            var pa = binding.loginPassword.text.toString()


            GlobalScope.launch(Dispatchers.Main) {

              //  appdb.registerDao().getAllUsers()
                var check : UserModel? = appdb.registerDao().findUser(em,pa)
                Log.d(TAG, "onCreate: ${check?.email}")

                if (check != null) {
                    if (check.email.equals(em) && check.pass.equals(pa))
                    {
                        val intent = Intent(this@K_register22, HomeAct_2::class.java) // start your next activity
                        startActivity(intent)


                    }

                    else
                    {


                    }
                }
            }





        })


    }



}

