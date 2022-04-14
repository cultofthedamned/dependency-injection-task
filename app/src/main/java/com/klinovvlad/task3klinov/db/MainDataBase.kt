package com.klinovvlad.task3klinov.db

import android.content.Context
import androidx.room.*
import com.klinovvlad.task3klinov.model.DataMain
import com.klinovvlad.task3klinov.model.DataResult

@Database(entities = [DataMain::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {
        @Volatile
        private var instance: MainDataBase? = null

        fun getInstance(context: Context): MainDataBase? {
            if (instance == null) {
                synchronized(MainDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDataBase::class.java,
                        "task3klinov.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}