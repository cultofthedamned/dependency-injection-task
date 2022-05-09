package com.klinovvlad.task3klinov.di.components

import com.klinovvlad.task3klinov.model.UserDatabaseRepository
import com.klinovvlad.task3klinov.model.UserNetworkRepository
import dagger.Component

@Component
interface ApplicationComponent {
    fun getDatabaseRepository(): UserDatabaseRepository
    fun getNetworkRepository(): UserNetworkRepository
}