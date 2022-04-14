package com.klinovvlad.task3klinov.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.model.DataResult
import com.klinovvlad.task3klinov.model.DataMain
import com.klinovvlad.task3klinov.network.instances.MainInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val mainInstance: MainInstance) : ViewModel() {

    val dataList = MutableLiveData<List<DataResult>>()

    fun getData() {
        val response = mainInstance.mainApi.getData()
        response.enqueue(object : Callback<DataMain?> {
            override fun onResponse(call: Call<DataMain?>, response: Response<DataMain?>) {
                dataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<DataMain?>, t: Throwable) {
                Log.d("error123", "2")
            }
        })
    }

}