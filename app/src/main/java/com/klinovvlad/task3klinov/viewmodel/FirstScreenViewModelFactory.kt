package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.model.UserRepository

class FirstScreenViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel(userRepository) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}