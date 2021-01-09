package com.task.noteapp.database.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "UserModel", indices = [Index(value = ["userEmail"], unique = true)])
data class UserModel(
    var userEmail:  String,
    var userPassword: String,
    var userName: String
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0

    constructor():this("","","")

    override fun toString(): String {
        return "UserModel(userEmail='$userEmail', userPassword='$userPassword', userName='$userName', userId=$userId)"
    }


}