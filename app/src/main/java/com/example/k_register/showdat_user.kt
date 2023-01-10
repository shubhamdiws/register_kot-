package com.example.k_register

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k_register.databinding.ActivityShowdatUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class showdat_user : AppCompatActivity() {
    private lateinit var Model: UserModel
    private lateinit var adapter: UserAdapter
    lateinit var appdb: AppDatabase
    private lateinit var binding: ActivityShowdatUserBinding

    var UserList  = listOf<UserModel>()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowdatUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // appdb=AppDatabase.getDatabase(this)

       appdb=AppDatabase.getDatabase(this)

            // this is co-routine
           GlobalScope.launch(Dispatchers.IO){
          //      //  appdb.registerDao.insertAll(UserModel(email,firstname,phone,null,setpassword))
                // i have to pass parameter image uri here
               UserList = appdb.registerDao().getAllUsers()
           //     Log.d(TAG, "onCreate: arrrr"+UserList.get(1).imageurl)

              // listCategory.add(UserModel("shubhudiws223@gmail.com","Diwakar shubham","9752798956",null,null,null))

                launch(Dispatchers.Main) {
                    val recyclerView = findViewById<RecyclerView>(R.id.main_recy1)
                    recyclerView.layoutManager = LinearLayoutManager(this@showdat_user)

                    // retreiving the data from list model which we created in dao/usermodel  or table
                    // if data is in list fetch list /if data is in live data fetch live data / main idea is to fetch data
                    // in that format only in which format we have stored our list .

                    val UserAdapter = UserAdapter(UserList = UserList)
                    Log.e("Dhaval", "onCreate: userlist : " + UserList)
                    for ( user in UserList){
                        Log.e(TAG, "onCreate: user ${user.name} " )
                    }
                    recyclerView.adapter = UserAdapter
                }
           }
     //   binding.mainRecy1.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
       // binding.mainRecy1.adapter=UserAdapter(this,listCategory)





    }




}