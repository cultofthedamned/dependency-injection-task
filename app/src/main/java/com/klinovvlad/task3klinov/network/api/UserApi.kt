package com.klinovvlad.task3klinov.network.api

import com.klinovvlad.task3klinov.model.UserNetworkEntity
import com.klinovvlad.task3klinov.utils.GET_MAIN_API
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET(GET_MAIN_API)
    fun getData(): Call<UserNetworkEntity>

}