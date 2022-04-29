package com.klinovvlad.task3klinov.network.api

import android.content.Context
import com.klinovvlad.task3klinov.model.UserNetworkEntity
import com.klinovvlad.task3klinov.utils.GET_MAIN_API
import com.klinovvlad.task3klinov.utils.MAIN_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApi {

    @GET(GET_MAIN_API)
    fun getData(): Call<UserNetworkEntity>

    companion object {
        @Volatile
        private var INSTANCE: UserApi? = null
        private val LOCK = Any()

        fun getInstance(): UserApi {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Retrofit.Builder()
                            .baseUrl(MAIN_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(UserApi::class.java)
                    }
                }
            }
            return INSTANCE!!
        }
    }

}