package com.task.noteapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.noteapp.database.model.UserModel

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: UserModel)

    @Query("SELECT * FROM UserModel")
    fun findAllUsers(): MutableList<UserModel>

    @Query("SELECT * FROM UserModel WHERE userEmail = :userEmail AND userPassword = :userPassword")
    fun getUserByEmailAndPassword(userEmail:String, userPassword:String) : MutableList<UserModel>

}