package com.example.newsapp.data

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Get the top headlines from a specific source (e.g., BBC)
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String = "bbc-news",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

}
