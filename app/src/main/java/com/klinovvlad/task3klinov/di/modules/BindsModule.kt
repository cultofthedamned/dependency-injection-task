package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.GetUsersDataDecorator
import com.klinovvlad.task3klinov.model.UsersDecorator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindUserDecorator(impl: GetUsersDataDecorator): UsersDecorator

}