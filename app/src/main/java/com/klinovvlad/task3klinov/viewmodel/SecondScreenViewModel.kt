package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserRepository

class SecondScreenViewModel(
    private val email: String,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _item = MutableLiveData<UserDatabaseEntity>()
    val item: LiveData<UserDatabaseEntity>
        get() = _item

    fun showItem() {
        Thread {
            _item.postValue(userRepository.getItem(email))
        }.start()
    }

}