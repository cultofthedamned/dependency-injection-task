package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserDecorator

class SecondScreenViewModel(
    private val uuid: String,
    private val userDecorator: UserDecorator
) : ViewModel() {

    private val _user = MutableLiveData<UserDatabaseEntity>()
    val user: LiveData<UserDatabaseEntity>
        get() = _user

    fun getUser() {
        userDecorator.getUser(
            uuid = uuid,
            onUserReceived = { user -> _user.postValue(user) }
        )
    }

}