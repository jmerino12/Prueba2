package com.jmb.data.source

import com.jmb.domain.User


interface RemoteDataSource {
    suspend fun getUsers(): List<User>
}