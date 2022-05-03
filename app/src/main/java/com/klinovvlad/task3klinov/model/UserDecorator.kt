package com.klinovvlad.task3klinov.model

import android.content.Context
import com.klinovvlad.task3klinov.db.UserDatabase
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.network.api.UserApi
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserDecorator {
    fun getUsers(
        offset: Int,
        onUsersReceived: (users: List<UserDatabaseEntity>) -> Unit,
        pageSize: Int
    )

    fun getUser(uuid: String, onUserReceived: (user: UserDatabaseEntity) -> Unit)
}

class GetUserDataDecorator(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userNetworkRepository: UserNetworkRepository
) : UserDecorator {
    private var pagingDataList: List<UserDatabaseEntity> = emptyList()

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
                val currentUsers = pagingDataList
                onUsersReceived(currentUsers + users)
                Thread {
                    if (currentUsers.isEmpty()) {
                        userDatabaseRepository.clearAllData()
                    }
                    userDatabaseRepository.insertData(users)
                }.start()
                pagingDataList = currentUsers + users
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    val currentUsers = pagingDataList
                    onUsersReceived(currentUsers + userDatabaseRepository.getPageData(offset))
                    pagingDataList = currentUsers + userDatabaseRepository.getPageData(offset)
                    // offset + pageSize
                }.start()
            }
        })
    }

    override fun getUser(uuid: String, onUserReceived: (user: UserDatabaseEntity) -> Unit) {
        Thread {
            onUserReceived(userDatabaseRepository.getItem(uuid))
        }.start()
    }

    companion object {
        @Volatile
        private var INSTANCE: GetUserDataDecorator? = null
        private val LOCK = Any()

        fun getInstance(context: Context): GetUserDataDecorator {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = GetUserDataDecorator(
                            UserDatabaseRepository(
                                UserDatabase.getInstance(context).mainDao()
                            ),
                            UserNetworkRepository(UserApi)
                        )
                    }
                }
            }
            return INSTANCE!!
        }
    }

}