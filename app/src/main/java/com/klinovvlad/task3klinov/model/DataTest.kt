package com.klinovvlad.task3klinov.model

import com.google.gson.annotations.SerializedName

data class DataTest(@SerializedName("results") val results: List<DataResult>)

data class DataResult(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: DataName,
    @SerializedName("location") val location: DataLocation,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: DataLogin,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: DataPicture
)

data class DataName(
    val title: String,
    val first: String,
    val last: String
)

data class DataLocation(
    val street: DataStreet,
    val city: String,
    val state: String,
    val country: String
)


data class DataStreet(
    val number: Int,
    val name: String
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