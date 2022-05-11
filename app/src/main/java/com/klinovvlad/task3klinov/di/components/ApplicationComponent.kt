package com.klinovvlad.task3klinov.di.components

import com.klinovvlad.task3klinov.di.modules.ContextModule
import com.klinovvlad.task3klinov.di.modules.DatabaseModule
import com.klinovvlad.task3klinov.di.modules.NetworkModule
import com.klinovvlad.task3klinov.view.ui.fragments.FirstScreenFragment
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        ContextModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: FirstScreenFragment)

}