package com.task.noteapp.updatenote

import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.home.HomeRepository
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

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class UpdateNoteRepositoryTest {

    //System Under Test
    private lateinit var sut: UpdateNoteRepository
    private lateinit var noteDAO: NoteDAO

    private val fixture = JFixture()

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private lateinit var noteModelMock: NoteModel



    @Before
    fun setUp() {
        noteDAO = mockk(relaxed = true)
        sut = UpdateNoteRepository(noteDAO)
        Dispatchers.setMain(dispatchers.main)
        noteModelMock = fixture()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()


    }
    @Test
    fun `refresh notes service should call update operation from the room  sqlite database return success`() = runBlockingTest{
        coVerify {
            sut.updateNote( noteModelMock)
            noteDAO.updateNoteTitleDescriptionAndCreateDateById(
                noteModelMock.noteTitle, noteModelMock.noteDesc, noteModelMock.noteCreateDate, noteModelMock.noteId
            )
        }
    }
}