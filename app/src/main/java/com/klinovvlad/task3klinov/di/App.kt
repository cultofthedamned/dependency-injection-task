package com.klinovvlad.task3klinov.di

import android.app.Application
import com.klinovvlad.task3klinov.di.modules.dataModule
import com.klinovvlad.task3klinov.di.modules.decoratorModule
import com.klinovvlad.task3klinov.di.modules.ViewModelsModule
import com.klinovvlad.task3klinov.di.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(dataModule, networkModule, decoratorModule, ViewModelsModule)
        }

    }

}