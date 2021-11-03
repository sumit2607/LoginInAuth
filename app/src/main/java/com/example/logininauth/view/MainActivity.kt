package com.example.logininauth.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.logininauth.R
import com.example.logininauth.SignupActivity
import com.example.logininauth.repo.LoginRepository
import com.example.logininauth.room.LoginDAO
import com.example.logininauth.room.LoginDatabase
import com.example.logininauth.room.LoginEntity
import com.example.logininauth.viewModel.LoginViewModel
import com.example.logininauth.viewModel.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    lateinit var dao: LoginDAO
    lateinit var repository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dao = LoginDatabase.getLoginDatabase(this).getLoginDAO()

        repository = LoginRepository(dao)

        val viewModelFactory =  LoginViewModelFactory(repository)


        val viewModel  =ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)


        TvSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        editbtn.setOnClickListener {
            val newUser = LoginEntity("newOne" , "NewEmail@xyzcom", 10, 989898, "qwert1234")
            newUser.id = 1

            CoroutineScope(Dispatchers.IO).launch {
                dao.updateUser(newUser)
            }
        }

        deletebtn.setOnClickListener {

            val deleteUser = LoginEntity("newOne" , "NewEmail@xyzcom", 10, 989898, "qwert1234")
            deleteUser.id = 2

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.delete(deleteUser)
            }
        }


        signInBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = viewModel.getUser(EtEmail.text.toString() , EtPassword.text.toString())
                if (user != null){
                    if (user?.email?.isNotEmpty()){
                       runOnUiThread {
                           Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                       }
                    }
                }
                else{
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()

                    }

                }
            }
        }
    }
}