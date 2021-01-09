package com.task.noteapp.di

import com.task.noteapp.createanote.CreateNoteRepository
import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.UserDAO
import com.task.noteapp.home.HomeRepository
import com.task.noteapp.login.LoginRepository
import com.task.noteapp.updatenote.UpdateNoteRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCreateANoteRepository(dao: NoteDAO): CreateNoteRepository {
        return CreateNoteRepository(dao)
    }

    fun provideHomeRepository(dao: NoteDAO): HomeRepository {
        return HomeRepository(dao)
    }

    fun provideUpdateNoteRepository(dao: NoteDAO): UpdateNoteRepository {
        return UpdateNoteRepository(dao)
    }


    fun provideLoginRepository(dao: UserDAO): LoginRepository {
        return LoginRepository(dao)
    }


    single { provideCreateANoteRepository(get()) }
    single { provideHomeRepository(get()) }
    single { provideUpdateNoteRepository(get()) }
    single { provideLoginRepository(get()) }

}