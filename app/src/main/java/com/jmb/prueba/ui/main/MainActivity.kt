package com.jmb.prueba.ui.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmb.domain.User
import com.jmb.prueba.databinding.ActivityMainBinding
import com.jmb.prueba.ui.common.UiModel
import com.jmb.prueba.ui.posts.PostActivity
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUsers()
        adapter = UserAdapter {
            val intent = Intent(this, PostActivity::class.java).apply {
                putExtra("idUser", it.id)
            }
            startActivity(intent)
        }
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)

        binding.recyclerViewSearchResults.adapter = adapter

        viewModel.users.observe(this, Observer(::updateUi))

        filterForName()
    }

    private fun filterForName() {
        binding.editTextSearch.doAfterTextChanged {
            adapter.filter.filter(it.toString())
        }
    }

    private fun updateUi(model: UiModel<List<User>>) {
        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> {
                adapter.users = ArrayList(model.data)
                adapter.listUsersCopy = ArrayList(model.data)
            }
            is UiModel.Error -> Log.e("Main", model.error.toString())
        }
    }


}