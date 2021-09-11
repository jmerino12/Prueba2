package com.jmb.prueba.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmb.domain.User
import com.jmb.prueba.ui.common.UiModel
import com.jmb.usecases.GetUsers
import kotlinx.coroutines.*

class MainViewModel(private val getUsers: GetUsers) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _users = MutableLiveData<UiModel<List<User>>>()
    val users: LiveData<UiModel<List<User>>> get() = _users

    fun getUsers() {
        uiScope.launch {
            _users.value = UiModel.Loading
            try {
                _users.value = UiModel.Content(getUsers.invoke())
            } catch (e: Exception) {
                _users.value = UiModel.Error(e)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }

}