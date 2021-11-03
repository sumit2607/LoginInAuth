package com.example.logininauth.room

import androidx.room.*

@Dao
interface LoginDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(loginEntity: LoginEntity)

    @Query("Select * From login_table where email = :email and password = :password" )
    fun getUser(email : String , password : String) : LoginEntity

    @Update
    fun updateUser(loginEntity: LoginEntity)

    @Delete
    fun deleteUser(loginEntity: LoginEntity)

}