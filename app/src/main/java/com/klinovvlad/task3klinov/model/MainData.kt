package com.klinovvlad.task3klinov.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import com.klinovvlad.task3klinov.db.Converters

data class DataMain(
    @SerializedName("results") val results: List<DataResult>
)

@Entity(tableName = "data_table")
data class DataResult(
    val gender: String,
    @SerializedName("name") val name: DataName,
    @PrimaryKey val email: String,
    @SerializedName("login") val login: DataLogin,
    val phone: String,
    @SerializedName("picture") val picture: DataPicture
)

data class DataName(
    val title: String,
    val first: String,
    val last: String
)

data class DataLogin(
    val uuid: String,
    val username: String,
    val password: String
)

data class DataPicture(
    val large: String,
    val medium: String
)