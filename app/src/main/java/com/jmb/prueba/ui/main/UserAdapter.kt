package com.jmb.prueba.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmb.domain.User
import com.jmb.prueba.databinding.UserListItemBinding
import com.jmb.prueba.ui.common.basicDiffUtil

class UserAdapter(private val listener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var users: List<User> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(viewBinding.root.rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(users[position])
        }
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = UserListItemBinding.bind(view)
        fun bind(user: User) = with(binding) {
            binding.name.text = user.name
            binding.phone.text = user.phone
            binding.email.text = user.email
            binding.btnViewPost.setOnClickListener {
                listener(user)
            }
        }
    }


}