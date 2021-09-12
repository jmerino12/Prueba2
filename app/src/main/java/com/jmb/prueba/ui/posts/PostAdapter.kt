package com.jmb.prueba.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmb.domain.Post
import com.jmb.prueba.databinding.PostListItemBinding
import com.jmb.prueba.ui.common.basicDiffUtil

class PostAdapter :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var posts: List<Post> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            PostListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(viewBinding.root.rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(posts[position])
        }
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = PostListItemBinding.bind(view)
        fun bind(post: Post) = with(binding) {
            binding.title.text = post.title
            binding.body.text = post.body
        }
    }


}