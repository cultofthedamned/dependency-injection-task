package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.ViewModel
import com.klinovvlad.task3klinov.model.*

class FirstScreenViewModel(userRepository: UserRepository) : ViewModel() {

    val userData = UserDataFromSource(Data(userRepository)).getData()

}