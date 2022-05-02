package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.utils.USER_DATABASE_LIMIT
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class UserDecorator {
    abstract fun getDataFromSource()
}

class GetUserDataDecorator(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userNetworkRepository: UserNetworkRepository,
    private val getData: (users: List<UserDatabaseEntity>) -> Unit
) : UserDecorator() {

    override fun getDataFromSource() {
        var usersList: List<UserDatabaseEntity>? = null
        val _offset: Int? = null
        val response = userNetworkRepository.getNetworkData()
        response.enqueue(object : Callback<UserNetworkEntity?> {
            override fun onResponse(
                call: Call<UserNetworkEntity?>,
                response: Response<UserNetworkEntity?>
            ) {
                val users = response.body()?.results?.map {
                    it.toUserDatabaseEntity()
                } ?: emptyList()
                getData(users)
                val currentUsers = usersList ?: emptyList()
                usersList = users
                Thread {
                    if(currentUsers.isEmpty()) {
                        userDatabaseRepository.clearAllData()
                    }
                    userDatabaseRepository.insertData(users)
                }.start()
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    val currentUsers = usersList ?: emptyList()
                    val offset = _offset ?: 0
                    getData(currentUsers + userDatabaseRepository.getPageData(offset))
                    offset + USER_DATABASE_LIMIT
                }.start()
            }
        })
    }

}

class UserDataDecorator(
    private val userDataHolder: UserDecorator
) : UserDecorator() {

    override fun getDataFromSource() {
        return userDataHolder.getDataFromSource()
    }

}