package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.network.api.UserApi
import retrofit2.Call

class UserNetworkRepository(private var userApi: UserApi) {

    fun getNetworkData(): Call<UserNetworkEntity> = userApi.getData()

}