package com.example.newsapp.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.newsapp.data.ApiService
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.model.Article

@Composable
fun NewsNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "newsScreen") {

        // Screen 1: List of Articles
        composable("newsScreen") {
            val repository = NewsRepository.create()
            val newsViewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory(repository))
            NewsScreen(viewModel = newsViewModel, navController = navController)
        }

        // Screen 2: Article Details
        composable(route = "article_detail?title={title}&url={url}&urlToImage={urlToImage}&content={content}&description={description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType; defaultValue = "" },
                navArgument("url") { type = NavType.StringType; defaultValue = "" },
                navArgument("urlToImage") { type = NavType.StringType; defaultValue = "" },
                navArgument("content") { type = NavType.StringType; defaultValue = "" },
                navArgument("description") { type = NavType.StringType; defaultValue = "" }
            )) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val url = backStackEntry.arguments?.getString("url") ?: ""
            val urlToImage = backStackEntry.arguments?.getString("urlToImage") ?: ""
            val content = backStackEntry.arguments?.getString("content") ?: ""
            val description = backStackEntry.arguments?.getString("content") ?: ""
            NewsDetailScreen(navController = navController, title, url, urlToImage, content, description)
        }
    }
}
