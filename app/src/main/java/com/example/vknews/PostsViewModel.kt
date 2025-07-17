package com.example.vknews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    fun loadPosts(onPostsLoaded: (List<Post>) -> Unit) {
        viewModelScope.launch {
            delay(2000) // Имитация загрузки
            val posts = List(50) { index ->
                Post(
                    id = index,
                    title = "Пост $index от Orel Codes",
                    description = "Описание поста $index.",
                    imageUrl = if (index % 2 == 0) "https://example.com/image$index.jpg" else null
                )
            }
            onPostsLoaded(posts)
        }
    }
}