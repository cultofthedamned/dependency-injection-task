package com.klinovvlad.task3klinov.model

import com.google.gson.annotations.SerializedName

data class DataTest(@SerializedName("results") val results: List<DataResult>)

data class DataResult(@SerializedName("name") val name: DataName)

data class DataName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)