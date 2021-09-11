package com.jmb.prueba.model.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun findById(id: Int): User

    @Query("SELECT COUNT(id) FROM User")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<User>)

    @Update
    fun updateUser(user: User)
}