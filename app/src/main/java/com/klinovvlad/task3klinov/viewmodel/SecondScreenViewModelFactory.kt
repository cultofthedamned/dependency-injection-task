package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.di.modules.SecondScreenViewModelComponent
import com.klinovvlad.task3klinov.model.GetUsersDataDecorator
import com.klinovvlad.task3klinov.model.UserDatabaseRepository
import com.klinovvlad.task3klinov.model.UserNetworkRepository
import javax.inject.Inject

class SecondScreenViewModelFactory(
    private val uuid: String,
    private val factory: SecondScreenViewModelComponent.Factory
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return factory.create(uuid).viewModel as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}