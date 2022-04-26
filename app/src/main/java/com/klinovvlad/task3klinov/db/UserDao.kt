package com.klinovvlad.task3klinov.db

import androidx.room.*
import com.klinovvlad.task3klinov.utils.USER_DATABASE_LIMIT

@Dao
interface UserDao {

    @Insert
    fun insertData(data: List<UserDatabaseEntity>)

    @Query("SELECT * FROM user WHERE uuid LIKE :uuid")
    fun getItemData(uuid: String?): UserDatabaseEntity

    @Query("SELECT * FROM user")
    fun getAllData(): List<UserDatabaseEntity>

    @Query("SELECT * FROM user LIMIT :limit OFFSET :offset")
    fun getPageData(offset: Int, limit: Int = USER_DATABASE_LIMIT): List<UserDatabaseEntity>

    @Query("DELETE FROM user")
    fun deleteAllData()

}