package com.klinovvlad.task3klinov.model

import com.klinovvlad.task3klinov.db.UserDao
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import javax.inject.Inject

class UserDatabaseRepository @Inject constructor(private val userDao: UserDao) {

    fun insertData(data: List<UserDatabaseEntity>) {
        userDao.insertData(data)
    }

    fun clearAllData() {
        userDao.deleteAllData()
    }

    fun getPageData(offset: Int): List<UserDatabaseEntity> {
        return userDao.getPageData(offset)
    }

    fun getItem(data: String): UserDatabaseEntity {
        return userDao.getItemData(data)
    }

}