package com.klinovvlad.task3klinov.di

import android.app.Application
import com.klinovvlad.task3klinov.di.components.ApplicationComponent
import com.klinovvlad.task3klinov.di.components.DaggerApplicationComponent
import com.klinovvlad.task3klinov.di.modules.ContextModule

class MyApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}