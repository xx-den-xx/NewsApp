package com.bda.news.data

import com.bda.database.models.ArticleDBO
import com.bda.news.data.model.Article
import com.bda.news.data.model.Source
import com.bda.newsapi.models.ArticleDTO
import com.bda.newsapi.models.SourceDTO
import com.bda.database.models.Source as SourceDBO

internal fun ArticleDBO.toArticle() : Article {
    return Article(
        cacheId = id,
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
}

internal fun SourceDBO.toSource(): Source {
    return Source(id = id, name = name)
}

internal fun SourceDTO.toSource(): Source {
    return Source(id = id ?: name, name = name)
}

internal fun SourceDTO.toSourceDbo(): SourceDBO {
    return SourceDBO(id = id ?: name, name = name)
}

internal fun ArticleDTO.toArticle() : Article {
    return Article(
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
}

internal fun ArticleDTO.toArticleDbo(): ArticleDBO {
    return ArticleDBO(
        source = source.toSourceDbo(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )}