package com.task.noteapp

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.task.noteapp.di.databaseModule
import com.task.noteapp.di.repositoryModule
import com.task.noteapp.di.viewModelModule
import com.task.noteapp.manager.SharedPreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val MAINAPPLICATIONTAG = "MAINAPPTAG"



class MainApplication: Application() {

    companion object {

        lateinit var sharedPreferencesManager: SharedPreferences

        private var mCurrentActivity: Activity? = null

        fun getApplication(): Companion {
            return this
        }

        fun setCurrentActivity(mCurrentActivity: Activity) {
            this.mCurrentActivity = mCurrentActivity
        }

        fun getCurrentActivity() = this.mCurrentActivity

    }

    override fun onCreate() {
        sharedPreferencesManager = SharedPreferencesManager.defaultPrefs(applicationContext)
        super.onCreate()
        startKoin {
            Log.i("tag", "Koin")
            androidContext(this@MainApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }

}