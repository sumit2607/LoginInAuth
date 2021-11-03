package com.example.logininauth.view

import android.app.Application
import com.example.logininauth.repo.LoginRepository
import com.example.logininauth.room.LoginDatabase

class LoginApplication: Application() {

    val loginDao by lazy {
        val roomDatabase = LoginDatabase.getLoginDatabase(this)
        roomDatabase.getLoginDAO()
    }
    val repository by lazy {
        LoginRepository(loginDao)
    }
}