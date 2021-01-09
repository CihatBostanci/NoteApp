package com.task.noteapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun getTimeNow(): String {
    val calendar = Calendar.getInstance();
    val dateFormat = SimpleDateFormat(DATE_PATTERN)
    return dateFormat.format(calendar.time)
}