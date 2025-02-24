package com.example.newsapp.ui

import app.cash.turbine.test
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel
    private lateinit var repository: NewsRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when repository returns articles, headlines are sorted by date`() = runTest {
        // Given
        val articles = listOf(
            Article("Title 1", "2024-03-02", "Desc 1", "Content 1", "url1", "image1"),
            Article("Title 2", "2024-03-03", "Desc 2", "Content 2", "url2", "image2"),
            Article("Title 3", "2024-03-01", "Desc 3", "Content 3", "url3", "image3")
        )
        coEvery { repository.getTopHeadlines() } returns NewsResponse(articles)

        // When
        viewModel = NewsViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.headlines.test {
            val result = awaitItem()
            assertEquals(3, result.size)
            assertEquals("Title 2", result[0].title) // Most recent
            assertEquals("Title 1", result[1].title)
            assertEquals("Title 3", result[2].title) // Oldest
        }
    }

    @Test
    fun `when repository returns null, headlines are empty`() = runTest {
        // Given
        coEvery { repository.getTopHeadlines() } returns null

        // When
        viewModel = NewsViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        viewModel.headlines.test {
            val result = awaitItem()
            assertTrue(result.isEmpty())
        }
    }
} 