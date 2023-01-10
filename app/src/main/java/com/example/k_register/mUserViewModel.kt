package com.example.k_register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers

class mUserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: userRepository

    init {
        val userDao = AppDatabase.getDatabase(application).registerDao()
        repository= userRepository(userDao)
    }









}