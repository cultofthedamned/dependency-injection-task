package com.klinovvlad.task3klinov.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.model.DataMain
import com.klinovvlad.task3klinov.network.instances.MainInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstScreenViewModel(private val mainInstance: MainInstance) : ViewModel() {

    val dataList = MutableLiveData<List<DataMain>>()

    fun getData() {
        val response = mainInstance.mainApi.getData()
        response.enqueue(object : Callback<List<DataMain>?> {
            override fun onResponse(
                call: Call<List<DataMain>?>,
                response: Response<List<DataMain>?>
            ) {
                dataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<DataMain>?>, t: Throwable) {
                Log.d("error123", "error1")
            }
        })
    }

}