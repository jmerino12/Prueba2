package com.jmb.prueba.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jmb.domain.User
import com.jmb.prueba.ui.common.UiModel
import com.jmb.testshared.mockedUser
import com.jmb.usecases.GetUsers
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getUsers: GetUsers

    @Mock
    lateinit var observer: Observer<UiModel<List<User>>>

    private lateinit var vm: MainViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        vm = MainViewModel(getUsers)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `on getUsers Category is called`() = runBlocking {
        val users = listOf(mockedUser.copy(id = 1))
        whenever(getUsers.invoke()).thenReturn(users)

        vm.users.observeForever(observer)

        vm.getUsers()


        verify(observer).onChanged(UiModel.Content(users))

    }

}