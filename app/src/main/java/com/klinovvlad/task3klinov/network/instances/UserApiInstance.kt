package com.klinovvlad.task3klinov.network.instances

import com.klinovvlad.task3klinov.network.api.UserApi
import com.klinovvlad.task3klinov.utils.MAIN_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiInstance {

    val userApi: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(MAIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

}