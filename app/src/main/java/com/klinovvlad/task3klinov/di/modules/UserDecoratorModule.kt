package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.GetUsersDataDecorator
import com.klinovvlad.task3klinov.model.UsersDecorator
import org.koin.dsl.module

val decoratorModule = module {

    factory<UsersDecorator> {
        GetUsersDataDecorator(
            userNetworkRepository = get(),
            userDatabaseRepository = get()
        )
    }

}