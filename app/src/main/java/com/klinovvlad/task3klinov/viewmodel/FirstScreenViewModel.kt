package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserNetworkEntity
import com.klinovvlad.task3klinov.model.UserNetworkEntity.UserResults
import com.klinovvlad.task3klinov.model.UserRepository
import com.klinovvlad.task3klinov.network.instances.UserApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _dataList = MutableLiveData<List<UserResults>>()
    val dataList: LiveData<List<UserResults>>
        get() = _dataList

    fun getDataFromNetwork() {
        val response = UserApiInstance.userApi.getData()
        response.enqueue(object : Callback<UserNetworkEntity?> {
            override fun onResponse(
                call: Call<UserNetworkEntity?>,
                response: Response<UserNetworkEntity?>
            ) {
                _dataList.postValue(response.body()?.results)
                Thread {
                    userRepository.clearAllData()
                    userRepository.insertData(response.body()?.results!!.toUserEntity())
                }.start()
            }

            override fun onFailure(call: Call<UserNetworkEntity?>, t: Throwable) {
                Thread {
                    _dataList.postValue(userRepository.allData.toUserResults())
                }.start()
            }
        })
    }

    fun List<UserResults>.toUserEntity(): List<UserDatabaseEntity> {
        val items = (0 until 30).map { i ->
            UserDatabaseEntity(
                gender = get(i).gender,
                title = get(i).name.title,
                first = get(i).name.first,
                last = get(i).name.last,
                email = get(i).email,
                uuid = get(i).login.uuid,
                username = get(i).login.username,
                password = get(i).login.password,
                phone = get(i).phone,
                large = get(i).picture.large,
                medium = get(i).picture.medium
            )
        }
        return items
    }

    fun List<UserDatabaseEntity>.toUserResults(): List<UserResults> {
        val items = (0 until 30).map { i ->
            UserResults(
                gender = get(i).gender,
                name = UserNetworkEntity.UserName(get(i).title, get(i).first, get(i).last),
                email = get(i).email,
                login = UserNetworkEntity.UserLogin(get(i).uuid, get(i).username, get(i).password),
                phone = get(i).phone,
                picture = UserNetworkEntity.UserPicture(get(i).large, get(i).medium)
            )
        }
        return items
    }

}