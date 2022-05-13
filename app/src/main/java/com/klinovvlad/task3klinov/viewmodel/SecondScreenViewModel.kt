package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UsersDecorator

class SecondScreenViewModel(
    private val uuid: String,
    private val usersDecorator: UsersDecorator
) : ViewModel() {

    private val _user = MutableLiveData<UserDatabaseEntity>()
    val user: LiveData<UserDatabaseEntity>
        get() = _user

    fun getUser() {
        usersDecorator.getUser(
            uuid = uuid,
            onUserReceived = { user -> _user.postValue(user) }
        )
    }

}