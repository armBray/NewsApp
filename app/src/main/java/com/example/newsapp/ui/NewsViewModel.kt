package com.example.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _headlines = MutableStateFlow<List<Article>>(emptyList())
    val headlines: StateFlow<List<Article>> get() = _headlines

    init {
        fetchHeadlines()
    }

    private fun fetchHeadlines() {
        viewModelScope.launch {
            val data = repository.getTopHeadlines()
            _headlines.value = data?.articles?.sortedByDescending { it.publishedAt } ?: emptyList() // Story 1.4 - Sorting headlines by date
        }
    }
}
