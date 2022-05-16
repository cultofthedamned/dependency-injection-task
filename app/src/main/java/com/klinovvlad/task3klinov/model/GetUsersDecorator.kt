package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GetUsersDecorator {
    fun getUsers(
        offset: Int,
        onUsersReceived: (users: List<UserDatabaseEntity>) -> Unit,
        pageSize: Int
    )
}

interface GetCertainUserDecorator {
    fun getUser(uuid: String, onUserReceived: (user: UserDatabaseEntity) -> Unit)
}

interface UsersDecorator : GetCertainUserDecorator, GetUsersDecorator

class GetUsersDataDecorator(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userNetworkRepository: UserNetworkRepository
) : UsersDecorator {

    override fun getUsers(
        offset: Int,
        onUsersReceived: (users: List<UserDatabaseEntity>) -> Unit,
        pageSize: Int
    ) {
        val response = userNetworkRepository.getNetworkData()
        response.enqueue(object : Callback<UserNetworkEntity?> {
            override fun onResponse(
                call: Call<UserNetworkEntity?>,
                response: Response<UserNetworkEntity?>
            ) {
                val users = response.body()?.results?.map {
                    it.toUserDatabaseEntity()
                } ?: emptyList()
                onUsersReceived(users)
                Thread {
                    if (offset == 0) {
                        userDatabaseRepository.clearAllData()
                    }
                    userDatabaseRepository.insertData(users)
                }.start()
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    onUsersReceived(userDatabaseRepository.getPageData(offset))
                }.start()
            }
        })
    }

    override fun getUser(uuid: String, onUserReceived: (user: UserDatabaseEntity) -> Unit) {
        Thread {
            onUserReceived(userDatabaseRepository.getItem(uuid))
        }.start()
    }

}