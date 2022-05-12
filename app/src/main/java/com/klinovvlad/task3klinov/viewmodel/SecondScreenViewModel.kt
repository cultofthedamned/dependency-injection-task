package com.klinovvlad.task3klinov.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task3klinov.db.UserDatabaseEntity
import com.klinovvlad.task3klinov.model.UsersDecorator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SecondScreenViewModel @AssistedInject constructor(
    @Assisted private val uuid: String,
    private val usersDecorator: UsersDecorator
) : ViewModel() {

    private val _user = MutableLiveData<UserDatabaseEntity>()
    val user: LiveData<UserDatabaseEntity>
        get() = _user

    fun getUser() {
        usersDecorator.getUser(
            uuid = uuid,
            onUserReceived = { user -> _user.postValue(user) }
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(uuid: String): SecondScreenViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            uuid: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
                    return assistedFactory.create(uuid) as T
                }
                throw IllegalArgumentException("UnknownViewModel")
            }
        }
    }

}