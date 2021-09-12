package com.jmb.prueba.model.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jmb.domain.Address
import com.jmb.domain.Company

@Entity
data class User(
    @PrimaryKey val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    @Ignore
    var address: Address? = null

    @Ignore
    var company: Company? = null
}
