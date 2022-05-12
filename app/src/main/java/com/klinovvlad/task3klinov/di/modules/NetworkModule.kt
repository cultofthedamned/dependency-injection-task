package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.network.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = UserApi.getInstance()

}