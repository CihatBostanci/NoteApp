package com.task.noteapp

import android.os.Bundle
import android.os.PersistableBundle

const val MAINACTIVITYTAG = "MAINACTTAG"

class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }



}