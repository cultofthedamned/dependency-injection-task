package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.model.UsersDecorator
import com.klinovvlad.task3klinov.viewmodel.SecondScreenViewModel
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module(
    subcomponents = [
        SecondScreenViewModelComponent::class
    ]
)
class ViewModelModule

@Subcomponent(
    modules = [SecondScreenViewModelModule::class]
)
interface SecondScreenViewModelComponent {

    val viewModel: SecondScreenViewModel

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance uuid: String): SecondScreenViewModelComponent

    }

}

@Module
class SecondScreenViewModelModule {

    @Provides
    fun getViewModel(
        uuid: String,
        decorator: UsersDecorator
    ): SecondScreenViewModel {
        return SecondScreenViewModel(
            uuid = uuid,
            usersDecorator = decorator
        )
    }

}