package com.klinovvlad.task3klinov.utils

import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserNetworkEntity.UserResults

fun List<UserResults>.toUserEntity(): List<UserDatabaseEntity> {
    val items = (0 until size).map { i ->
        UserDatabaseEntity(
            gender = get(i).gender,
            title = get(i).name.title,
            first = get(i).name.first,
            last = get(i).name.last,
            email = get(i).email,
            uuid = get(i).login.uuid,
            username = get(i).login.username,
            password = get(i).login.password,
            phone = get(i).phone,
            large = get(i).picture.large,
            medium = get(i).picture.medium
        )
    }
    return items
}