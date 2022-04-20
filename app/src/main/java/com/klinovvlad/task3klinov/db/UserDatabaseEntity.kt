package com.klinovvlad.task3klinov.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDatabaseEntity(
    val gender: String,
    val title: String,
    val first: String,
    val last: String,
    val email: String,
    @PrimaryKey val uuid: String,
    val username: String,
    val password: String,
    val phone: String,
    val large: String,
    val medium: String
)