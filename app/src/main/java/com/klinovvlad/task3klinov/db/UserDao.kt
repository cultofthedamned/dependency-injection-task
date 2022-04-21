package com.klinovvlad.task3klinov.db

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertData(data: List<UserDatabaseEntity>)

    @Query("SELECT * FROM user WHERE uuid LIKE :uuid")
    fun getItemData(uuid: String?): UserDatabaseEntity

    @Query("SELECT * FROM user")
    fun getAllData(): List<UserDatabaseEntity>

    @Query("DELETE FROM user")
    fun deleteAllData()

}