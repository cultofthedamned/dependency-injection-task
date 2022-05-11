package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.UserNetworkRepository
import com.klinovvlad.task3klinov.network.api.UserApi
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideUserApi(): UserApi = UserApi.getInstance()

}