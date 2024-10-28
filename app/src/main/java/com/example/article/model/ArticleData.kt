package com.example.article.model

data class ArticleData(
    val body: String,
    val created_at: String,
    val id: String,
    val image: String,
    val is_like: Int,
    val thumbnail: String,
    val title: String,
    val total_likes: Int,
    val type: String,
    val user_id: String
)