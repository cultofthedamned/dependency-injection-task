package com.klinovvlad.task3klinov.db

import androidx.room.*
import com.klinovvlad.task3klinov.model.DataResult

@Database(entities = [DataResult::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainDataBase : RoomDatabase() {

    abstract fun mainDao(): MainDao

}