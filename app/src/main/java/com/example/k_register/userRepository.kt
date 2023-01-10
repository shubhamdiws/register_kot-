package com.example.k_register

import androidx.lifecycle.LiveData

class userRepository(private val registerDao: registerDao) {

suspend fun insertAll(userModel: UserModel){

    registerDao.insertAll(userModel)
}

}