package com.jmb.prueba.model.server

import retrofit2.http.GET
import retrofit2.http.Query

interface TheUserDbService {
    @GET("users")
    suspend fun getListUsers(): List<User>

    @GET("posts")
    suspend fun getPostByUserId(
        @Query("userId") idUser: String
    ): List<Post>
}