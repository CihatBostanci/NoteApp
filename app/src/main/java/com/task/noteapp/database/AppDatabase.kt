package com.task.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.database.model.UserModel

@Database(entities = [NoteModel::class,UserModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteDAO: NoteDAO
    abstract val userDAO: UserDAO
}