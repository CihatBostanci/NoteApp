package com.task.noteapp.createanote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.NoteDAO
import com.task.noteapp.database.model.NoteModel
import com.task.noteapp.invoke
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateNoteRepositoryTest {

    //System Under Test
    private lateinit var sut: CreateNoteRepository
    private lateinit var noteDAO: NoteDAO

    private val fixture = JFixture()

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private var data = NoteModel()

    @Before
    fun setUp() {
        noteDAO = mockk(relaxed = true)
        sut = CreateNoteRepository(noteDAO)
        Dispatchers.setMain(dispatchers.main)
        data = fixture()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `create note service should call insert operation to  the adding room  sqlite database return success`() = runBlockingTest{
            coVerify {
                sut.addNote(data)
                noteDAO.add(data)
            }
    }



}