package com.klinovvlad.task3klinov.model

import com.google.gson.annotations.SerializedName

data class DataTest(@SerializedName("results") val results: List<DataResult>)

data class DataResult(@SerializedName("name") val name: DataName)

data class DataName(
    val title: String,
    val first: String,
    val last: String
)