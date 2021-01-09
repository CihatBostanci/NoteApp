package com.task.noteapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.task.noteapp.utils.ProgressDisplay

const val BASEACTIVITYTAG = "BASEACTTAG"

abstract class BaseActivity : AppCompatActivity(), ProgressDisplay {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()
        MainApplication.getApplication().setCurrentActivity(this)
    }
    var progressBar: ProgressBar? = null

    override fun show() {
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hide() {
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.INVISIBLE
    }

    fun showToast(message:String?){
        Toast.makeText(this, message ?: "Message is null", Toast.LENGTH_LONG).show()
    }

}