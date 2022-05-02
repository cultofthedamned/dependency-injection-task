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

    private val _item = MutableLiveData<UserDatabaseEntity>()
    val item: LiveData<UserDatabaseEntity>
        get() = _item

    fun postUser() {
        userDecorator.getUserData(
            uuid = uuid,
            getUser = { user -> _item.postValue(user) }
        )
    }

}