package com.jmb.data.repository

import com.jmb.data.source.LocalDataSource
import com.jmb.data.source.RemoteDataSource
import com.jmb.domain.Post
import com.jmb.domain.User

class UsersRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getUsers(): List<User> {
        if (localDataSource.isEmpty()) {
            val users = remoteDataSource.getUsers()
            localDataSource.saveUsers(users)
        }
        return localDataSource.getUsers()
    }

    suspend fun findById(id: Int): User = localDataSource.findById(id)

    suspend fun findByIdPost(idUser: String): List<Post> = remoteDataSource.getPostByUserId(idUser)



}