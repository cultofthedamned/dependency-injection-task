package com.klinovvlad.task3klinov.di.components

import com.klinovvlad.task3klinov.di.modules.*
import com.klinovvlad.task3klinov.view.ui.fragments.FirstScreenFragment
import com.klinovvlad.task3klinov.view.ui.fragments.SecondScreenFragment
import dagger.Component

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        BindsModule::class,
        ContextModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: FirstScreenFragment)
    fun inject(fragment: SecondScreenFragment)

}