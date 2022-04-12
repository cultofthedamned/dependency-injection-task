package com.klinovvlad.task3klinov.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.model.DataTest
import com.klinovvlad.task3klinov.network.instances.MainInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val mainInstance: MainInstance) : ViewModel() {

    val dataList = MutableLiveData<List<DataTest>>()

    fun getData() {
        val response = mainInstance.mainApi.getData()
        response.enqueue(object : Callback<List<DataTest>?> {
            override fun onResponse(
                call: Call<List<DataTest>?>,
                response: Response<List<DataTest>?>
            ) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<DataTest>?>, t: Throwable) {
                Log.d("error123", "error1")
            }
        })
    }

}