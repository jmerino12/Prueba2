package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.testshared.mockedPost
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostForUserIdTest {

    @Mock
    lateinit var repository: UsersRepository

    lateinit var getPostForUserId: GetPostForUserId

    @Before
    fun setUp() {
        getPostForUserId = GetPostForUserId(repository)
    }

    @Test
    fun `invoke calls users repository`() = runBlocking {
        val posts = listOf(mockedPost.copy(id = 1))

        whenever(repository.findByIdPost("1")).thenReturn(posts)

        val result = getPostForUserId.invoke("1")

        Assert.assertEquals(posts, result)
    }
}