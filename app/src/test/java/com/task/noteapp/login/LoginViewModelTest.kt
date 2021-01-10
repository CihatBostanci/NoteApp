package com.task.noteapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.task.noteapp.UnitTestCoroutineDispatcherProvider
import com.task.noteapp.database.model.UserModel
import com.task.noteapp.invoke
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: LoginViewModel
    private lateinit var loginRepository: LoginRepository

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    private lateinit var userModelMock: UserModel
    private lateinit var userEmailMock: String
    private lateinit var userPasswordMock: String


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers.main)

        loginRepository = mockk(relaxed = true)
        sut = LoginViewModel(loginRepository)

        userModelMock = fixture()
        userEmailMock = fixture()
        userPasswordMock = fixture()

    }


    @Test
    fun `fetch all users should call get from repository`() = runBlockingTest {

        sut.fetchDataAllUsers()
        coVerify {
            loginRepository.fetchAllUsers()
        }

    }


    @Test
    fun `add user should call create operation to the repository`() = runBlockingTest {

        sut.addUser(userModelMock)
        coVerify {
            loginRepository.addUser(userModelMock)
        }

    }

    @Test
    fun `get user should call to the repository`() = runBlockingTest {

        sut.getUserByEmailAndPassword(userEmailMock,userPasswordMock)
        coVerify {
            loginRepository.getUserByEmailAndPassword(userEmailMock, userPasswordMock)
        }

    }
}