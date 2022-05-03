package com.klinovvlad.task3klinov.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.model.GetUserDataDecorator

class FirstScreenViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel(
                GetUserDataDecorator.getInstance(context)
            ) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}