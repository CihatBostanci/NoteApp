package com.task.noteapp.di

import android.app.Application
import androidx.room.Room
import com.task.noteapp.database.AppDatabase
import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.UserDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

        fun provideDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        fun provideNoteDao(database: AppDatabase): NoteDAO{
            return database.noteDAO
        }
        fun provideUserDao(database: AppDatabase): UserDAO{
        return database.userDAO
        }

        single { provideDatabase(androidApplication()) }
        single { provideNoteDao(get()) }
        single { provideUserDao(get()) }

}