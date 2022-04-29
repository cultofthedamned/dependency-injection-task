package com.klinovvlad.task3klinov.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.network.api.UserApi
import com.klinovvlad.task3klinov.utils.USER_DATABASE_LIMIT
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class UserDecorator : UserDataHolder() {
    abstract fun getDataFromSource(): LiveData<List<UserDatabaseEntity>>
}

abstract class UserDataHolder {
    abstract fun getData(): LiveData<List<UserDatabaseEntity>>
}

class GetUserDataDecorator(private val userRepository: UserRepository) : UserDataHolder() {
    private val _offset: Int? = null
    private val _dataList = MutableLiveData<List<UserDatabaseEntity>>()
    private val dataList: LiveData<List<UserDatabaseEntity>>
        get() = _dataList

    override fun getData(): LiveData<List<UserDatabaseEntity>> {
        val response = UserApi.getInstance().getData()
        response.enqueue(object : Callback<UserNetworkEntity?> {
            override fun onResponse(
                call: Call<UserNetworkEntity?>,
                response: Response<UserNetworkEntity?>
            ) {
                val users = response.body()?.results?.map {
                    it.toUserDatabaseEntity()
                } ?: emptyList()
                val currentUsers = _dataList.value ?: emptyList()
                _dataList.postValue(currentUsers + users)
                Thread {
                    if (currentUsers.isEmpty()) {
                        userRepository.clearAllData()
                    }
                    userRepository.insertData(users)
                }.start()
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    val currentUsers = _dataList.value ?: emptyList()
                    val offset = _offset ?: 0
                    _dataList.postValue(currentUsers + userRepository.getPageData(offset))
                    offset + USER_DATABASE_LIMIT
                }.start()
            }
        })
        return dataList
    }

}

class UserDataDecorator(
    private val userDataHolder: UserDataHolder
) : UserDecorator() {

    override fun getDataFromSource(): LiveData<List<UserDatabaseEntity>> {
        return userDataHolder.getData()
    }

    override fun getData(): LiveData<List<UserDatabaseEntity>> {
        return getDataFromSource()
    }

}