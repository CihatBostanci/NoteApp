package com.task.noteapp.login

import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.UserDAO
import com.task.noteapp.database.model.UserModel
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
class LoginRepositoryTest {


    //System Under Test
    private lateinit var sut: LoginRepository
    private lateinit var userDAO: UserDAO

    private val fixture = JFixture()

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private lateinit var userModelMock: UserModel
    private lateinit var userEmailMock: String
    private lateinit var userPasswordMock: String


    @Before
    fun setUp() {
        userDAO = mockk(relaxed = true)
        sut = LoginRepository(userDAO)
        Dispatchers.setMain(dispatchers.main)
        userModelMock = fixture()
        userEmailMock = fixture()
        userPasswordMock = fixture()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch all user service should call get operation from the room  sqlite database return success`() =
        runBlockingTest {
            sut.fetchAllUsers()
            coVerify {
                userDAO.findAllUsers()
            }
        }


    @Test
    fun `add user service should call insert operation to the room  sqlite database return success`() =
        runBlockingTest {
            coVerify {
                sut.addUser(userModelMock)
                userDAO.add(userModelMock)
            }
        }

    @Test
    fun `fetch user service should call  get operation from the room  sqlite database return success`() =
        runBlockingTest {

            userDAO.getUserByEmailAndPassword(userEmailMock, userPasswordMock)[0]
            coVerify {
                sut.getUserByEmailAndPassword(userEmailMock, userPasswordMock)
            }

        }
}