package com.klinovvlad.task3klinov.network.instances

import com.klinovvlad.task3klinov.network.api.MainApi
import com.klinovvlad.task3klinov.utils.MAIN_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainInstance {

    val mainApi: MainApi by lazy {
        Retrofit.Builder()
            .baseUrl(MAIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainApi::class.java)
    }

}