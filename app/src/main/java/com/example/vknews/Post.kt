package com.example.vknews

data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String? = null
)