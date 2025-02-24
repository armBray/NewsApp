package com.example.newsapp.model

data class Article(
    val title: String,
    val publishedAt: String,
    val description: String?,
    val content: String?,
    val url: String,
    val urlToImage: String?
)
