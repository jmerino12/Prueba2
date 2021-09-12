package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.testshared.mockedUser
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetDataUserByIdTes {

    @Mock
    lateinit var repository: UsersRepository

    lateinit var getDataUserById: GetDataUserById

    @Before
    fun setUp() {
        getDataUserById = GetDataUserById(repository)
    }

    @Test
    fun `invoke calls users repository`() {
        runBlocking {

            val movie = mockedUser.copy(id = 1)
            whenever(repository.findById(1)).thenReturn(movie)

            val result = getDataUserById.invoke(1)

            assertEquals(movie, result)
        }
    }

}