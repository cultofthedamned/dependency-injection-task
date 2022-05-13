package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.db.UserDatabase
import com.klinovvlad.task3klinov.model.UserDatabaseRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        UserDatabase.getInstance(context = get())
    }

    single {
        UserDatabase.getInstance(context = get()).mainDao()
    }

    single {
        UserDatabaseRepository(userDao = get())
    }

}