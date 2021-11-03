package com.example.logininauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.logininauth.repo.LoginRepository
import com.example.logininauth.room.LoginDAO
import com.example.logininauth.room.LoginDatabase
import com.example.logininauth.room.LoginEntity
import com.example.logininauth.view.LoginApplication
import com.example.logininauth.viewModel.LoginViewModel
import com.example.logininauth.viewModel.LoginViewModelFactory
import kotlinx.android.synthetic.main.register_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    lateinit var database : LoginDatabase
    lateinit var dao: LoginDAO
    lateinit var repository: LoginRepository
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        dao = LoginDatabase.getLoginDatabase(this).getLoginDAO()

        repository = LoginRepository(dao)

        val viewModelFactory =  LoginViewModelFactory(repository)


        val viewModel  =ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)


        signUpBtn.setOnClickListener {

            val signUpData = LoginEntity(
                nameeEt.text.toString(),
                emaillEt.text.toString(),
                ageEt.text.toString().toInt(),
                mobileEt.text.toString().toInt(),
                passworddEt.text.toString())

            viewModel.newRegister(signUpData)


        }


    }
}