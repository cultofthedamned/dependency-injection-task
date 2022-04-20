package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.db.UserDao
import com.klinovvlad.task3klinov.db.UserDatabaseEntity

class UserRepository(private val userDao: UserDao) {

    val allData = userDao.getAllData()

    fun insertData(data: List<UserDatabaseEntity>) {
        userDao.insertData(data)
    }

    fun clearAllData() {
        userDao.deleteAllData()
    }

    fun getItem(data: String): UserDatabaseEntity {
        return userDao.getItemData(data)
    }

}