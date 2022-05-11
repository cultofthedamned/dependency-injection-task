package com.klinovvlad.task3klinov.db

import android.content.Context
import androidx.room.*
import com.klinovvlad.task3klinov.utils.USER_DATABASE

@Database(entities = [UserDatabaseEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun mainDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): UserDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            UserDatabase::class.java,
                            USER_DATABASE
                        ).allowMainThreadQueries().build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}