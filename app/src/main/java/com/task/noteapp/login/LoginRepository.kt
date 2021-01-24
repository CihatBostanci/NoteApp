package com.task.noteapp.login

import com.task.noteapp.database.UserDAO
import com.task.noteapp.database.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository (private val userDAO: UserDAO){

    fun fetchAllUsers(): MutableList<UserModel> {
        return userDAO.findAllUsers()
    }

    suspend fun addUser( user: UserModel) {
        withContext(Dispatchers.IO) {
            userDAO.add(user)
        }
    }

    fun getUserByEmailAndPassword(userEmail: String, userPassword:String):UserModel {
     return userDAO.getUserByEmailAndPassword(userEmail, userPassword)[0]
    }




}