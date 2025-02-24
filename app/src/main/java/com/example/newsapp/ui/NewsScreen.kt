package com.example.newsapp.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.model.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(viewModel: NewsViewModel, navController: NavController) {
    val headlines by viewModel.headlines.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BBC News") }) // Story 1.1 - Title "BBC News" is shown here
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) { // Story 1.2|1.5 - Presents headlines in a list format | LazyColumn enables scrolling through the list
            items(headlines) { article ->
                NewsItem(article = article, onItemClick = {
                    // Story 2.1 - Navigate to the detail screen and pass the article data
                    navController.navigate("article_detail?title=${Uri.encode(article.title)}" +
                            "&url=${Uri.encode(article.url)}" +
                            "&urlToImage=${Uri.encode(article.urlToImage)}" +
                            "&content=${Uri.encode(article.content)}" +
                            "&description=${Uri.encode(article.description)}")
                })
            }
        }
    }
}

@Composable
fun NewsItem(article: Article, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage), // Story 1.6 - Downloads & Caches Image
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Text(text = article.title, style = MaterialTheme.typography.headlineSmall) // Story 1.3 - Headline title for each cell
        }
    }
}
