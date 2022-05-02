package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.model.UserDatabaseRepository

class SecondScreenViewModelFactory(
    private val uuid: String,
    private val userDatabaseRepository: UserDatabaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel(uuid, userDatabaseRepository) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}