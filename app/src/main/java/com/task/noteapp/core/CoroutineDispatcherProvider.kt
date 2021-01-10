package com.task.noteapp.core


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
        get() = Dispatchers.Main
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    class Default : CoroutineDispatcherProvider
}