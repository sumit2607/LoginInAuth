package com.example.logininauth.viewModel

import androidx.lifecycle.ViewModel
import com.example.logininauth.repo.LoginRepository
import com.example.logininauth.room.LoginEntity

class LoginViewModel(val repository : LoginRepository )  : ViewModel(){

    fun newRegister(loginEntity: LoginEntity){
        repository.register(loginEntity)
    }

    fun getUser(email : String , password : String) : LoginEntity{
        return repository.getUser(email, password)
    }

    fun update(loginEntity: LoginEntity){
        return repository.update(loginEntity)
    }

    fun  delete(loginEntity: LoginEntity){
        return repository.delete(loginEntity)
    }
}