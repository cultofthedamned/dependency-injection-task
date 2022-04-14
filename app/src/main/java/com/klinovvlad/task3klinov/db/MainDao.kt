package com.klinovvlad.task3klinov.db

import androidx.room.*
import com.klinovvlad.task3klinov.model.DataMain
import com.klinovvlad.task3klinov.model.DataResult

@Dao
interface MainDao {

    @Insert
    fun insertData(data: DataMain)

    @Query("SELECT * FROM data_table")
    fun getData(): List<DataMain>

    @Update
    fun updateData(data: DataMain)

    @Delete
    fun deleteData(data: DataMain)

}