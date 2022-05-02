package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.network.api.UserApi
import retrofit2.Call

class UserNetworkRepository(private var userApi: UserApi.Companion) {

    fun getNetworkData(): Call<UserNetworkEntity> = userApi.getInstance().getData()

}