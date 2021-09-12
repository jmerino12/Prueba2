package com.jmb.prueba.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jmb.domain.User
import com.jmb.prueba.databinding.UserListItemBinding
import com.jmb.prueba.ui.common.basicDiffUtil

class UserAdapter(private val listener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    var users: ArrayList<User> by basicDiffUtil(
        ArrayList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )
    lateinit var listUsersCopy: ArrayList<User>


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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.isEmpty()) {
                    filterResults.count = listUsersCopy.size
                    filterResults.values = listUsersCopy
                } else {
                    val search = constraint.toString().lowercase()

                    val userList = ArrayList<User>()
                    for (item in listUsersCopy) {
                        if (item.name.lowercase().contains(search)) {
                            userList.add(item)
                        }
                    }
                    filterResults.count = userList.size
                    filterResults.values = userList
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                users = results!!.values as ArrayList<User>
                notifyItemRangeChanged(0, users.size)
            }

        }
    }


}