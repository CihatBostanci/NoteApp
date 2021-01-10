package com.task.noteapp

import com.flextrade.jfixture.JFixture

inline operator fun <reified T> JFixture.invoke(): T = create(T::class.java)