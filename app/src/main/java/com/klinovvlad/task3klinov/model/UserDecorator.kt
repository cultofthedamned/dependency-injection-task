package com.klinovvlad.task3klinov.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.klinovvlad.task3klinov.db.UserDatabase
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.network.api.UserApi
import com.klinovvlad.task3klinov.utils.USER_DATABASE_LIMIT
import com.klinovvlad.task3klinov.utils.toUserDatabaseEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface UserDecorator {
    fun getUsersData(getData: (users: List<UserDatabaseEntity>) -> Unit)
    fun getUserData(uuid: String, getUser: (user: UserDatabaseEntity) -> Unit)
}

class GetUserDataDecorator(
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userNetworkRepository: UserNetworkRepository,
    private var pagingOffset: Int = 0,
    private var pagingDataList: List<UserDatabaseEntity> = emptyList()
) : UserDecorator {

    override fun getUsersData(getData: (users: List<UserDatabaseEntity>) -> Unit) {
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
                getData(currentUsers + users)
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
                    getData(currentUsers + userDatabaseRepository.getPageData(pagingOffset))
                    pagingDataList = currentUsers + userDatabaseRepository.getPageData(pagingOffset)
                    pagingOffset += USER_DATABASE_LIMIT
                }.start()
            }
        })
    }

    override fun getUserData(uuid: String, getUser: (user: UserDatabaseEntity) -> Unit) {
        Thread {
            getUser(userDatabaseRepository.getItem(uuid))
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