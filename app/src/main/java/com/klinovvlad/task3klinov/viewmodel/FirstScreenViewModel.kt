package com.klinovvlad.task3klinov.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.*

class FirstScreenViewModel(
    private val userDecorator: UserDecorator
) : ViewModel() {

    private val _dataList = MutableLiveData<List<UserDatabaseEntity>>()
    val dataList: LiveData<List<UserDatabaseEntity>>
        get() = _dataList

    fun postData() {
        //GetUserDataDecorator.getInstance(Application()).getUsersData { users ->
        //    _dataList.postValue(users)
        //}
        userDecorator.getUsersData { users -> _dataList.postValue(users) }
    }

}