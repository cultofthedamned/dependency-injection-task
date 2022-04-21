package com.klinovvlad.task3klinov.utils

import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UserNetworkEntity.UserResults

fun UserResults.toUserDatabaseEntity(): UserDatabaseEntity {
    return UserDatabaseEntity(
        gender = gender,
        title = name.title,
        first = name.first,
        last = name.last,
        email = email,
        uuid = login.uuid,
        username = login.username,
        password = login.password,
        phone = phone,
        large = picture.large,
        medium = picture.medium
    )
}