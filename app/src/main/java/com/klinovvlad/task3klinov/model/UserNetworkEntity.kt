package com.klinovvlad.task3klinov.model

data class UserNetworkEntity(val results: List<UserResults>) {

    data class UserResults(
        val gender: String,
        val name: UserName,
        val email: String,
        val login: UserLogin,
        val phone: String,
        val picture: UserPicture
    )

    data class UserName(val title: String, val first: String, val last: String)

    data class UserLogin(val uuid: String, val username: String, val password: String)

    data class UserPicture(val large: String, val medium: String)

}
