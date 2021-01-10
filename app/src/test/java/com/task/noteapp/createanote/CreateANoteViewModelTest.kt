package com.task.noteapp.createanote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.model.NoteModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import com.task.noteapp.invoke

import org.junit.Rule

@ExperimentalCoroutinesApi
class CreateANoteViewModelTest {


    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: CreateANoteViewModel
    private lateinit var createNoteRepository: CreateNoteRepository

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    private lateinit var noteModelMock : NoteModel


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers.main)

        createNoteRepository = mockk(relaxed = true)

        sut = CreateANoteViewModel( createNoteRepository  )

        noteModelMock = fixture()
    }

    @Test
    fun `add note should call create note`() = runBlockingTest {

        sut.addNote(noteModelMock)

        coVerify {
            createNoteRepository.addNote(noteModelMock)
        }
    }

}