package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.*

class FirstScreenViewModel(
    userDatabaseRepository: UserDatabaseRepository,
    userNetworkRepository: UserNetworkRepository
) : ViewModel() {

    private val _dataList = MutableLiveData<List<UserDatabaseEntity>>()
    val dataList: LiveData<List<UserDatabaseEntity>>
        get() = _dataList

    val userData =
        UserDataDecorator(
            GetUserDataDecorator(
                userDatabaseRepository,
                userNetworkRepository,
                getData = {
                    _dataList.postValue(it)
                }
            )
        ).getDataFromSource()
}