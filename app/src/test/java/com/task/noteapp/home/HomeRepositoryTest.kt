package com.task.noteapp.home

import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.NoteDAO
import com.task.noteapp.invoke
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    //System Under Test
    private lateinit var sut: HomeRepository
    private lateinit var noteDAO: NoteDAO

    private val fixture = JFixture()

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private var userIdMock : Int = 0
    private var noteIdMock : Int = 0


    @Before
    fun setUp() {
        noteDAO = mockk(relaxed = true)
        sut = HomeRepository(noteDAO)
        Dispatchers.setMain(dispatchers.main)
        userIdMock = fixture()
        noteIdMock = fixture()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch all notes service should call get operation from the room  sqlite database return success`() = runBlockingTest{
        sut.fetchAllNotes(userIdMock)
        coVerify {
            noteDAO.findAll(userIdMock)
        }
    }


    @Test
    fun `delete  notes service should call remove operation from the room  sqlite database return success`() = runBlockingTest{
        coVerify {
            sut.deleteNote(noteIdMock)
            noteDAO.deleteByNoteId(noteIdMock)
        }
    }

}