package com.jmb.prueba.ui.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmb.domain.Post
import com.jmb.domain.User
import com.jmb.prueba.databinding.ActivityPostBinding
import com.jmb.prueba.ui.common.UiModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostActivity : ScopeActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var adapter: PostAdapter


    private val viewModel: PostViewModel by viewModel {
        parametersOf(intent.getIntExtra("idUser", -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        adapter = PostAdapter()
        setContentView(binding.root)

        viewModel.findUser()
        viewModel.getPostList()
        viewModel.user.observe(this, Observer(::updateUi))

        binding.recyclerViewPostsResults.layoutManager = LinearLayoutManager(this)

        binding.recyclerViewPostsResults.adapter = adapter

        viewModel.posts.observe(this, Observer(::updatePost))

    }

    private fun updateUi(model: UiModel<User>) {
        when (model) {
            is UiModel.Content -> {
                loadData(model.data)
            }
            is UiModel.Error -> Log.e("UserError", model.error.toString())
        }
    }

    private fun updatePost(model: UiModel<List<Post>>) {
        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> {
                adapter.posts = ArrayList(model.data)
            }
            is UiModel.Error -> Log.e("PostError", model.error.toString())
        }
    }

    private fun loadData(data: User) {
        binding.name.text = data.name
        binding.email.text = data.email
        binding.phone.text = data.phone

    }
}