package com.task.noteapp.updatenote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.database.model.UserModel
import com.task.noteapp.home.HomeViewModel
import com.task.noteapp.invoke
import com.task.noteapp.login.LoginRepository
import com.task.noteapp.login.LoginViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

@ExperimentalCoroutinesApi
class UpdateNoteViewModelTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: UpdateNoteViewModel
    private lateinit var updateNoteRepository: UpdateNoteRepository

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    private lateinit var noteModelMock: NoteModel

    @Before
    fun setUp() {

        Dispatchers.setMain(dispatchers.main)

        updateNoteRepository = mockk(relaxed = true)

        sut = UpdateNoteViewModel(updateNoteRepository)

        noteModelMock = fixture()

    }

    @Test
    fun updateNoteItem() = runBlockingTest{
        sut.updateNoteItem(noteModelMock)
        coVerify {
            updateNoteRepository.updateNote(noteModelMock)
        }
    }
}