package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.domain.User

class FindPostsByUserId(private val userrepository: UsersRepository) {
    suspend fun invoke(id: Int): User = userrepository.findById(id)
}