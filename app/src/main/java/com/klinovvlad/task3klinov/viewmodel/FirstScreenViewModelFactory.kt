package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.network.instances.MainInstance

class FirstScreenViewModelFactory(private val mainInstance: MainInstance) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel(this.mainInstance) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}