package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.model.GetUsersDataDecorator
import com.klinovvlad.task3klinov.model.UserDatabaseRepository
import com.klinovvlad.task3klinov.model.UserNetworkRepository
import javax.inject.Inject

class FirstScreenViewModelFactory @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userNetworkRepository: UserNetworkRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel(
                GetUsersDataDecorator.getInstance(
                    userDatabaseRepository,
                    userNetworkRepository
                )
            ) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}