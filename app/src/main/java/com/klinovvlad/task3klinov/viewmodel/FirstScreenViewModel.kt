package com.klinovvlad.task3klinov.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.klinovvlad.task3klinov.db.MainDataBase
import com.klinovvlad.task3klinov.model.DataResult
import com.klinovvlad.task3klinov.model.DataMain
import com.klinovvlad.task3klinov.network.instances.MainInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val mainInstance: MainInstance) : ViewModel() {

    val dataList = MutableLiveData<List<DataResult>>()

    fun getDataFromNetwork(context: Context) {
        val response = mainInstance.mainApi.getData()
        val db = Room.databaseBuilder(
            context,
            MainDataBase::class.java,
            "task3klinov.db"
        ).build()
        response.enqueue(object : Callback<DataMain?> {
            override fun onResponse(call: Call<DataMain?>, response: Response<DataMain?>) {
                dataList.postValue(response.body()?.results)
                Thread {
                    db.clearAllTables()
                    db.mainDao().insertData(response.body()?.results)
                }.start()
            }

            override fun onFailure(call: Call<DataMain?>, t: Throwable) {
                Thread {
                    dataList.postValue(db.mainDao().getAllData())
                }.start()
            }
        })
    }
}