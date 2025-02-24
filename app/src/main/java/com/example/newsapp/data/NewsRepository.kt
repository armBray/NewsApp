package com.example.newsapp.data

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.utils.Constants
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsRepository(private val api: ApiService) {
    companion object {
        fun create(): NewsRepository {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return NewsRepository(retrofit.create(ApiService::class.java))
        }
    }

    suspend fun getTopHeadlines(): NewsResponse? {
        val response = api.getTopHeadlines()
        return if (response.isSuccessful) response.body() else null
    }
}
