package com.task.noteapp

import android.app.Activity
import android.app.Application

const val MAINAPPLICATIONTAG = "MAINAPPTAG"

class MainApplication: Application() {

    companion object {

        private var mCurrentActivity: Activity? = null

        fun getApplication(): Companion {
            return this
        }

        fun setCurrentActivity(mCurrentActivity: Activity) {
            this.mCurrentActivity = mCurrentActivity
        }

        fun getCurrentActivity() = this.mCurrentActivity

    }

}