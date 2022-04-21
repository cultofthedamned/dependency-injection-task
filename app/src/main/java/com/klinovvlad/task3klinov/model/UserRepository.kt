package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.db.UserDao
import com.klinovvlad.task3klinov.db.UserDatabaseEntity

class UserRepository(private val userDao: UserDao) {

    fun insertData(data: List<UserDatabaseEntity>) {
        userDao.insertData(data)
    }

    fun clearAllData() {
        userDao.deleteAllData()
    }

    fun getAllData(): List<UserDatabaseEntity> {
        return userDao.getAllData()
    }

    fun getItem(data: String): UserDatabaseEntity {
        return userDao.getItemData(data)
    }

}