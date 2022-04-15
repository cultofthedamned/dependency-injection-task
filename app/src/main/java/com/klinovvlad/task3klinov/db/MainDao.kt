package com.klinovvlad.task3klinov.db

import androidx.room.*
import com.klinovvlad.task3klinov.model.DataResult

@Dao
interface MainDao {

    @Insert
    fun insertData(data: List<DataResult>?)

    @Query("SELECT * FROM data_table WHERE email LIKE :email")
    fun getItemData(email: String?): DataResult

    @Query("SELECT * FROM data_table")
    fun getAllData(): List<DataResult>

}