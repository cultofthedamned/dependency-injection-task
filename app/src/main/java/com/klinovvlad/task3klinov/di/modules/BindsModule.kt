package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.GetUsersDataDecorator
import com.klinovvlad.task3klinov.model.UsersDecorator
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindUserDecorator(impl: GetUsersDataDecorator): UsersDecorator

}