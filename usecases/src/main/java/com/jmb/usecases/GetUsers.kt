package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsers(private val usersRepository: UsersRepository) {
    suspend fun invoke(): List<User> = withContext(Dispatchers.IO) {
        usersRepository.getUsers()
    }

}