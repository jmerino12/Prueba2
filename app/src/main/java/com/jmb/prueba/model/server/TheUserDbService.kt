package com.jmb.prueba.model.server

import retrofit2.http.GET

interface TheUserDbService {

    @GET("users")
    suspend fun getListUsers() : List<User>

}