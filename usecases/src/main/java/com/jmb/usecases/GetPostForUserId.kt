package com.jmb.usecases

import com.jmb.data.repository.UsersRepository
import com.jmb.domain.Post

class GetPostForUserId(private val usersRepository: UsersRepository) {
    suspend fun invoke(idUser: String): List<Post> = usersRepository.findByIdPost(idUser)
}