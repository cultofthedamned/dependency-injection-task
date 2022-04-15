package com.klinovvlad.task3klinov.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.klinovvlad.task3klinov.db.MainDataBase
import com.klinovvlad.task3klinov.model.DataResult

class SecondScreenViewModel(private val email: String) : ViewModel() {

    val item = MutableLiveData<DataResult>()

    fun showItem(context: Context) {
        val db = Room.databaseBuilder(
            context,
            MainDataBase::class.java,
            "task3klinov.db"
        ).build()
        Thread {
            item.postValue(db.mainDao().getItemData(email))
        }.start()
    }

}