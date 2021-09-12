package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.testshared.mockedUser
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUsersTest {

    @Mock
    lateinit var repository: UsersRepository

    lateinit var getUsers: GetUsers

    @Before
    fun setUp() {
        getUsers = GetUsers(repository)
    }

    @Test
    fun `invoke calls users repository`() = runBlocking {
        val users = listOf(mockedUser.copy(id = 1))

        whenever(repository.getUsers()).thenReturn(users)

        val result = getUsers.invoke()

        Assert.assertEquals(users, result)
    }

}