package com.jmb.prueba.model.database

import com.jmb.data.source.LocalDataSource
import com.jmb.domain.User
import com.jmb.prueba.model.toDomainUser
import com.jmb.prueba.model.toRoomMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: UserDatabase) : LocalDataSource {

    private val movieDao = db.userDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }


    override suspend fun saveUsers(users: List<User>) {
        withContext(Dispatchers.IO) { movieDao.insertUsers(users.map { it.toRoomMovie() }) }
    }

    override suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        movieDao.getAll().map { it.toDomainUser() }
    }

    override suspend fun findById(id: Int): User = withContext(Dispatchers.IO) {
        movieDao.findById(id).toDomainUser()
    }

    override suspend fun update(user: User) = withContext(Dispatchers.IO) {
        movieDao.updateUser(user.toRoomMovie())
    }


}