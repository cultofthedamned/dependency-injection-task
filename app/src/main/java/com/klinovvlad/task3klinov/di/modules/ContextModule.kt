package com.klinovvlad.task3klinov.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @Provides
    fun providesApplicationContext(): Context = application

}