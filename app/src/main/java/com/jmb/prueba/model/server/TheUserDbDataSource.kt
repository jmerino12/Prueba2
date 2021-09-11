package com.jmb.prueba.model.server

import com.jmb.data.source.RemoteDataSource
import com.jmb.domain.User
import com.jmb.prueba.model.toDomainMovie

class TheUserDbDataSource(private val userDB: UserDB) : RemoteDataSource {

    override suspend fun getUsers(): List<User> {
        return userDB.webServicesMain.getListUsers().map {
            it.toDomainMovie()
        }
    }

}