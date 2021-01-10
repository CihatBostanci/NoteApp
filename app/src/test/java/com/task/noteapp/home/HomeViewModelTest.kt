package com.task.noteapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.invoke
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: HomeViewModel
    private lateinit var homeRepository: HomeRepository

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    private  var noteIdMock= 0
    private  var userIdMock= 0


    @Before
    fun setUp() {

        Dispatchers.setMain(dispatchers.main)

        homeRepository = mockk(relaxed = true)

        sut = HomeViewModel(homeRepository)

        userIdMock = fixture()
        noteIdMock = fixture()

    }


    @Test
    fun `fetch all notes should call get from repository`() = runBlockingTest {

        sut.fetchDataAllNotes(userIdMock)

        coVerify {
            homeRepository.fetchAllNotes(userIdMock)
        }
    }


    @Test
    fun `delete  note should remove from repository by id`() = runBlockingTest {

        sut.deleteNoteItem(noteIdMock)

        coVerify {
            homeRepository.deleteNote(noteIdMock)
        }
    }

}