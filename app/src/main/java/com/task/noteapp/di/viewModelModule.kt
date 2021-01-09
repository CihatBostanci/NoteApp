package com.task.noteapp.di

import com.task.noteapp.createanote.CreateANoteViewModel
import com.task.noteapp.home.HomeViewModel
import com.task.noteapp.login.LoginViewModel
import com.task.noteapp.updatenote.UpdateNoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { CreateANoteViewModel (createNoteRepository = get()) }
    viewModel { HomeViewModel (homeRepository = get()) }
    viewModel { UpdateNoteViewModel (updateNoteRepository = get()) }
    viewModel { LoginViewModel (loginRepository = get()) }

}