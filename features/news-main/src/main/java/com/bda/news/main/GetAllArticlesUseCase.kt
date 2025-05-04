package com.bda.news.main

import com.bda.news.data.ArticlesRepository
import com.bda.news.data.RequestResult
import com.bda.news.data.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bda.news.data.model.Article as DataArticle

internal class GetAllArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository,
) {

    operator fun invoke(query: String): Flow<RequestResult<List<ArticleUI>>> {
        return repository.getAll(query)
            .map { requestResult ->
                requestResult.map { articles -> articles.map { it.toUiArticle() } }
            }
    }
}

private fun DataArticle.toUiArticle(): ArticleUI {
    return ArticleUI(
        id = cacheId,
        title = title,
        description = description,
        imageUrl = urlToImage,
        url = url,
    )
}