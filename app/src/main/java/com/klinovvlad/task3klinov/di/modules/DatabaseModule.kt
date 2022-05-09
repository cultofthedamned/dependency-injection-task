package com.klinovvlad.task3klinov.di.modules

import android.app.Application
import com.klinovvlad.task3klinov.db.UserDao
import com.klinovvlad.task3klinov.db.UserDatabase
import com.klinovvlad.task3klinov.model.UserDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabaseRepository(): UserDatabaseRepository =
        UserDatabaseRepository(UserDatabase.getInstance(Application()).mainDao())
}