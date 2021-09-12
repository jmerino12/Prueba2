package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.domain.User

class GetDataUserById(private val usersRepository: UsersRepository) {
    suspend fun invoke(id: Int): User = usersRepository.findById(id)
}