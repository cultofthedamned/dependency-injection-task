package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserNetworkEntity
import com.klinovvlad.task3klinov.model.UserRepository
import com.klinovvlad.task3klinov.network.instances.UserApiInstance
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _dataList = MutableLiveData<List<UserDatabaseEntity>>()
    val dataList: LiveData<List<UserDatabaseEntity>>
        get() = _dataList

    fun getUsers() {
        val response = UserApiInstance.userApi.getData()
        response.enqueue(object : Callback<UserNetworkEntity?> {
            override fun onResponse(
                call: Call<UserNetworkEntity?>,
                response: Response<UserNetworkEntity?>
            ) {
                val users = response.body()?.results?.map {
                    it.toUserDatabaseEntity()
                } ?: emptyList()
                _dataList.postValue(users)
                Thread {
                    userRepository.clearAllData()
                    userRepository.insertData(users)
                }.start()
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    _dataList.postValue(userRepository.getAllData())
                }.start()
            }
        })
    }

}