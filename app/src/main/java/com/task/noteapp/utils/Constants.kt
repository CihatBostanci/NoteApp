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