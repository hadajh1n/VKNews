package com.example.vknews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vknews.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настраиваем RecyclerView
        adapter = PostsAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = adapter

        // Инициализируем ViewModel
        viewModel = ViewModelProvider(this)[PostsViewModel::class.java]

        // Загружаем посты
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadPosts { posts ->
            binding.progressBar.visibility = View.GONE
            binding.postsRecyclerView.visibility = View.VISIBLE
            adapter.setPosts(posts)
        }
    }
}