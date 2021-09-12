package com.jmb.data.repository

import com.jmb.data.source.LocalDataSource
import com.jmb.data.source.RemoteDataSource
import com.jmb.testshared.mockedPost
import com.jmb.testshared.mockedUser
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var repository: UsersRepository

    @Before
    fun setUp() {
        repository = UsersRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getUser gets from local data source first`() {
        runBlocking {

            val localUsers = listOf(mockedUser.copy(id = 1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getUsers()).thenReturn(localUsers)

            val result = repository.getUsers()

            assertEquals(localUsers, result)
        }
    }

    @Test
    fun `getUser saves remote data to local`() {
        runBlocking {

            val remoteUsers = listOf(mockedUser.copy(id = 2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getUsers()).thenReturn(remoteUsers)

            repository.getUsers()

            verify(localDataSource).saveUsers(remoteUsers)
        }
    }

    @Test
    fun `findById calls local data source`() {
        runBlocking {

            val user = mockedUser.copy(id = 5)
            whenever(localDataSource.findById(5)).thenReturn(user)

            val result = repository.findById(5)

            assertEquals(user, result)
        }
    }

    @Test
    fun `findPost byUserId calls remote data source`() {
        runBlocking {

            val posts = listOf(mockedPost.copy(id = 5))
            whenever(remoteDataSource.getPostByUserId(any())).thenReturn(posts)

            val result = repository.findByIdPost("1")

            assertEquals(posts, result)
        }
    }

}