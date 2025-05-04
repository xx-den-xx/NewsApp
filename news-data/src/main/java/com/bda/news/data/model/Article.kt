package com.bda.news.data.model

import java.util.Date

class Article(
    val cacheId: Long = ID_NONE,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String,
) {
    private companion object {
        private const val ID_NONE = 0L
    }
}

data class Source(
    val id: String,
    val name: String,
)
