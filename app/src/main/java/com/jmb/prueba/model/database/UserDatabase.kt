package com.jmb.prueba.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user-db"
        ).build()
    }

    abstract fun userDao(): UserDao
}