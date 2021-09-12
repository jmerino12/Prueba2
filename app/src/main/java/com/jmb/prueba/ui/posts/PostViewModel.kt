package com.jmb.prueba.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jmb.domain.Post
import com.jmb.domain.User
import com.jmb.prueba.ui.common.UiModel
import com.jmb.usecases.GetDataUserById
import com.jmb.usecases.GetPostForUserId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostViewModel(
    private val userId: Int,
    private val getDataUserById: GetDataUserById,
    private val getPostForUserId: GetPostForUserId
) :
    ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _user = MutableLiveData<UiModel<User>>()
    val user: LiveData<UiModel<User>> get() = _user

    private val _posts = MutableLiveData<UiModel<List<Post>>>()
    val posts: LiveData<UiModel<List<Post>>> get() = _posts

    fun findUser() {
        uiScope.launch {
            _user.value = UiModel.Loading
            try {
                _user.value = UiModel.Content(getDataUserById.invoke(userId))
            } catch (e: Exception) {
                _user.value = UiModel.Error(e)
            }
        }
    }

    fun getPostList() {
        uiScope.launch {
            _posts.value = UiModel.Loading
            try {
                _posts.value = UiModel.Content(getPostForUserId.invoke(userId.toString()))
            } catch (e: Exception) {
                _posts.value = UiModel.Error(e)
            }
        }
    }
}