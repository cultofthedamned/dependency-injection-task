package com.klinovvlad.task3klinov.db

import android.content.Context
import androidx.room.*
import com.klinovvlad.task3klinov.utils.USER_DATABASE

@Database(entities = [UserDatabaseEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun mainDao(): UserDao

    companion object {

        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            USER_DATABASE
        ).allowMainThreadQueries().build()

    }

}