package com.example.newsapp.data

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryTest {
    private lateinit var repository: NewsRepository
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mockk()
        repository = NewsRepository(apiService)
    }

    @Test
    fun `getTopHeadlines returns news response when API call is successful`() = runTest {
        // Given
        val articles = listOf(
            Article("Test Title", "2024-03-01", "Test Desc", "Test Content", "url", "imageUrl")
        )
        val newsResponse = NewsResponse(articles)
        coEvery { apiService.getTopHeadlines(any(), any()) } returns Response.success(newsResponse)

        // When
        val result = repository.getTopHeadlines()

        // Then
        assertEquals(newsResponse, result)
    }

    @Test
    fun `getTopHeadlines returns null when API call fails`() = runTest {
        // Given
        coEvery { apiService.getTopHeadlines(any(), any()) } returns Response.error(
            404,
            mockk(relaxed = true)
        )

        // When
        val result = repository.getTopHeadlines()

        // Then
        assertNull(result)
    }
} 