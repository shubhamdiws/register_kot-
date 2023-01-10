package com.example.k_register

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface registerDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertAll(userModel: UserModel)

    @Query("SELECT * FROM User34 ")
     suspend fun getAllUsers(): List<UserModel>
    // fun getAlluSers(): List<UserModel>

    @Query(
        "SELECT * FROM User34 WHERE email LIKE :email AND " +
                "password LIKE :pass "
    )

  suspend fun findUser(email: String?, pass: String?): UserModel?


}

