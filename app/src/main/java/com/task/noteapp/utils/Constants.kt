package com.task.noteapp.utils

import java.util.regex.Matcher
import java.util.regex.Pattern


//*****************************************************************
//Validation Pattern
val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun isValidPassword(password: String?): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    pattern = Pattern.compile(passwordPattern)
    matcher = pattern.matcher(password)
    return matcher.matches()
}

//*****************************************************************
//Validation Error Message
const val PASSWORD_INVALID_MESSAGE = "Password is not valid."
const val EMAIL_INVALID_MESSAGE = "Email is not valid."
const val USER_NAME_INVALID_MESSAGE = "User name is not valid. Please give your name length 2-12 characters"
const val NOTE_TITLE_INVALID_MESSAGE = "Note title is not valid. Please give your name length 2-12 characters"
const val NOTE_DESCRIPTION_INVALID_MESSAGE = "Note description is not valid. Please give your name length 2-40 characters"



//*****************************************************************
//Shared User Constants
const val USER_EMAIL = "useremail"
const val USER_PASSWORD = "password"
const val USER_NAME= "username"
const val USER_ID = "userId"


//*****************************************************************
//Date Pattern
const val DATE_PATTERN = "MM/dd/yyyy hh:mm:ss"

//*****************************************************************
//Generic Message
const val SUCCESS_MESSAGE = "Success"
const val DELETE_MESSAGE = "Deleted"
const val ADDED_SUCCESS = "User Added successfully"


//*****************************************************************
//Bundle Keys
const val CLOSE_MODAL_BOTTOM_SHEET_KEY = "CloseClickListener"
const val UPDATE_KEY = "Update"