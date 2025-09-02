package com.bda.news.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.bda.news.NewsTheme

@Composable
fun NewsMainScreen() {
    NewsMainScreen(viewModel = viewModel())
}

@Composable
internal fun NewsMainScreen(viewModel: NewsMainViewModel) {
    val state by viewModel.state.collectAsState()
    val currentState = state
    Column {
        when (currentState) {
            is State.Success -> currentState.articles?.let { Articles(it) }
            is State.Error -> ErrorMessage(currentState)
            is State.Loading -> ProgressIndicator(currentState)
            State.None -> NewsEmpty()
        }
        if (currentState.articles != null) {
            Articles(articles = currentState.articles)
        }
    }
}

@Composable
internal fun ErrorMessage(state: State.Error) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(NewsTheme.colorScheme.error)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error during update", color = NewsTheme.colorScheme.onError)
    }
}

@Composable
internal fun ProgressIndicator(state: State.Loading) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun NewsEmpty() {
    Column {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text("No news")
    }
        }
}

@Preview
@Composable
private fun Articles(
    @PreviewParameter(ArticlesPreviewProvider::class, limit = 1) articles: List<ArticleUI>
) {
    LazyColumn {
        items(articles) { article ->
            key(article.id) {
                Article(article)
            }
        }
    }
}

@Preview
@Composable
internal fun Article(
    @PreviewParameter(ArticlePreviewProvider::class, limit = 1) article: ArticleUI
) {
    Row(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        article.imageUrl?.let { imageUrl ->
            var isImageVisible by remember { mutableStateOf(true) }
            if (isImageVisible) {
                AsyncImage(
                    model = imageUrl,
                    onState = { state ->
                        if (state is AsyncImagePainter.State.Error) {
                            isImageVisible = false
                        }
                    },
                    contentDescription = stringResource(R.string.content_desc_item_article_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(4.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = article.title,
                style = NewsTheme.typography.headlineMedium,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = article.description,
                style = NewsTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }
}

private class ArticlePreviewProvider : PreviewParameterProvider<ArticleUI> {

    override val values = sequenceOf(
        ArticleUI(
            id = 1,
            title = "Android Studio Iguana 1 is Stable!",
            description = "New stable version on Android IDE has been release",
            imageUrl = null,
            url = ""
        ),
        ArticleUI(
            id = 2,
            title = "Android Studio Iguana 2 is Stable!",
            description = "New stable version on Android IDE has been release",
            imageUrl = null,
            url = ""
        ),
        ArticleUI(
            id = 3,
            title = "Android Studio Iguana 3 is Stable!",
            description = "New stable version on Android IDE has been release",
            imageUrl = null,
            url = ""
        ),
    )

}

private class ArticlesPreviewProvider : PreviewParameterProvider<List<ArticleUI>> {

    private val articleProvider = ArticlePreviewProvider()

    override val values = sequenceOf(
        articleProvider.values
            .toList()
    )

}
