package com.jmb.prueba.model.server

import com.jmb.data.source.RemoteDataSource
import com.jmb.domain.Post
import com.jmb.domain.User
import com.jmb.prueba.model.toDomainMovie
import com.jmb.prueba.model.toDomainPost

class TheUserDbDataSource(private val userDB: UserDB) : RemoteDataSource {

    override suspend fun getUsers(): List<User> {
        return userDB.webServicesMain.getListUsers().map {
            it.toDomainMovie()
        }
    }

    override suspend fun getPostByUserId(idUser: String): List<Post> {
        return userDB.webServicesMain.getPostByUserId(idUser).map {
            it.toDomainPost()
        }
    }

}