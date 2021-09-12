package com.jmb.prueba.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmb.domain.User
import com.jmb.prueba.ui.common.UiModel
import com.jmb.usecases.FindPostsByUserId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostViewModel(private val userId: Int, private val findPostsByUserId: FindPostsByUserId) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _user = MutableLiveData<UiModel<User>>()
    val user: LiveData<UiModel<User>> get() = _user

     fun findMovie() {
        uiScope.launch {
            _user.value = UiModel.Loading
            try {
                _user.value = UiModel.Content(findPostsByUserId.invoke(userId))
            } catch (e: Exception) {
                _user.value = UiModel.Error(e)
            }
        }
    }
}