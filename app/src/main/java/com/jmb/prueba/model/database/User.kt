package com.jmb.prueba.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmb.domain.Address
import com.jmb.domain.Company

@Entity
data class User(
    val address: Address,
    val company: Company,
    val email: String,
    @PrimaryKey val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)
