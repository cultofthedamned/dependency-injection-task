package com.klinovvlad.task3klinov.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.model.GetUsersDataDecorator

class SecondScreenViewModelFactory(
    private val uuid: String,
    private val context: Context
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel(
                uuid, GetUsersDataDecorator.getInstance(context)
            ) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}