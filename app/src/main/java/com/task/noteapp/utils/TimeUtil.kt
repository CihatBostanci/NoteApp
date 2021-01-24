package com.task.noteapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getTimeNow(): String {
    val calendar = Calendar.getInstance();
    val dateFormat = SimpleDateFormat(DATE_PATTERN)
    return dateFormat.format(calendar.time)
}