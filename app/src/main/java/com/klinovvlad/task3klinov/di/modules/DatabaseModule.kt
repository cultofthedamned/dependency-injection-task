package com.klinovvlad.task3klinov.di.modules

import android.content.Context
import com.klinovvlad.task3klinov.db.UserDao
import com.klinovvlad.task3klinov.db.UserDatabase
import com.klinovvlad.task3klinov.model.UsersDecorator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase =
        UserDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.mainDao()

}