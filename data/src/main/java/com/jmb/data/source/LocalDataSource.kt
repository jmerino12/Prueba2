package com.jmb.data.source

import com.jmb.domain.User


interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveUsers(users: List<User>)
    suspend fun getUsers(): List<User>
    suspend fun findById(id: Int): User
    suspend fun update(user: User)
}