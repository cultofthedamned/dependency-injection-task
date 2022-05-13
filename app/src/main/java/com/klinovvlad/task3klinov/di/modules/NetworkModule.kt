package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.UserNetworkRepository
import com.klinovvlad.task3klinov.network.api.UserApi
import org.koin.dsl.module

val networkModule = module {

    single {
        UserApi.getInstance()
    }

    single {
        UserNetworkRepository(userApi = get())
    }

}