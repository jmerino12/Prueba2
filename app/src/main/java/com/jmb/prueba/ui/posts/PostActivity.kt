package com.jmb.prueba.ui.posts

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.jmb.domain.User
import com.jmb.prueba.databinding.ActivityPostBinding
import com.jmb.prueba.ui.common.UiModel
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostActivity : ScopeActivity() {

    private lateinit var binding: ActivityPostBinding

    private val viewModel: PostViewModel by viewModel{
        parametersOf(intent.getIntExtra("idUser", -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.findMovie()

        viewModel.user.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel<User>){
        //binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content ->{loadData(model.data)}
            is UiModel.Error -> Log.e("Post",model.error.toString())
        }
    }

    private fun loadData(data: User) {
        binding.name.text = data.name
        binding.email.text = data.email
        binding.phone.text = data.phone

    }
}