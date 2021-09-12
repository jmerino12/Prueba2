package com.jmb.data.source

import com.jmb.domain.Post
import com.jmb.domain.User


interface RemoteDataSource {
    suspend fun getUsers(): List<User>
    suspend fun getPostByUserId(idUser: String): List<Post>

}