package com.klinovvlad.task3klinov.db

import androidx.room.TypeConverter
import com.klinovvlad.task3klinov.model.DataLogin
import com.klinovvlad.task3klinov.model.DataName
import com.klinovvlad.task3klinov.model.DataPicture
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun dataNameToJson(value: DataName): String {
        return JSONObject().apply {
            put("title", value.title)
            put("first", value.first)
            put("last", value.last)
        }.toString()
    }

    @TypeConverter
    fun dataNameFromJson(data: String): DataName {
        val json = JSONObject(data)
        return DataName(
            json.getString("title"),
            json.getString("first"),
            json.getString("last")
        )
    }

    @TypeConverter
    fun dataLoginToJson(value: DataLogin): String {
        return JSONObject().apply {
            put("uuid", value.uuid)
            put("username", value.username)
            put("password", value.password)
        }.toString()
    }

    @TypeConverter
    fun dataLoginFromJson(data: String): DataLogin {
        val json = JSONObject(data)
        return DataLogin(
            json.getString("uuid"),
            json.getString("username"),
            json.getString("password")
        )
    }

    @TypeConverter
    fun dataPictureToJson(value: DataPicture): String {
        return JSONObject().apply {
            put("medium", value.medium)
            put("large", value.large)
        }.toString()
    }

    @TypeConverter
    fun dataPictureFromJson(data: String): DataPicture {
        val json = JSONObject(data)
        return DataPicture(
            json.getString("medium"),
            json.getString("large")
        )
    }

}