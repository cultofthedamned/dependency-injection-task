package com.klinovvlad.task3klinov.di.modules

import com.klinovvlad.task3klinov.viewmodel.FirstScreenViewModel
import com.klinovvlad.task3klinov.viewmodel.SecondScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firstScreenViewModelModule = module {

    viewModel {
        FirstScreenViewModel(usersDecorator = get())
    }

    viewModel { (uuid: String) ->
        SecondScreenViewModel(
            uuid = uuid,
            usersDecorator = get()
        )
    }

}