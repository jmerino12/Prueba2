package com.jmb.data.repository

import com.jmb.data.source.RemoteDataSource
import com.jmb.domain.User

class UsersRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getUsers():List<User>{
        return remoteDataSource.getUsers()
    }
}