package com.example.vknews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vknews.databinding.ItemPostBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private val posts = mutableListOf<Post>()

    fun setPosts(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postTitle.text = post.title
            binding.postDescription.text = post.description
            if (post.imageUrl != null) {
                binding.postImage.visibility = View.VISIBLE
                binding.postImage.setImageDrawable(null) // Заглушка для изображения
                binding.postImage.contentDescription = "Картинка поста"
            } else {
                binding.postImage.visibility = View.GONE
            }
        }
    }
}