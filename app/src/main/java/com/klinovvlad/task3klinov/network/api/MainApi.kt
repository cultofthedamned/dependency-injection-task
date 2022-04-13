package com.klinovvlad.task3klinov.network.api

import com.klinovvlad.task3klinov.model.DataTest
import retrofit2.Call
import retrofit2.http.GET

interface MainApi {

    @GET("api/?results=30")
    fun getData(): Call<DataTest>

}