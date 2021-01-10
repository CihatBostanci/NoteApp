package com.task.noteapp

import com.task.noteapp.core.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UnitTestCoroutineDispatcherProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}