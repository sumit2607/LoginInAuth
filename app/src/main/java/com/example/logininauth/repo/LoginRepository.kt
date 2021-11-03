package com.example.logininauth.repo

import com.example.logininauth.room.LoginDAO
import com.example.logininauth.room.LoginEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository(val loginDAO: LoginDAO) {

    fun register (loginEntity: LoginEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            loginDAO.register(loginEntity)
        }
    }

    fun getUser(email: String , password : String) : LoginEntity{
            return loginDAO.getUser(email, password)
        }


    fun update(loginEntity: LoginEntity){
            return loginDAO.updateUser(loginEntity)
        }


    fun delete(loginEntity: LoginEntity){
            return loginDAO.deleteUser(loginEntity)
        }


}