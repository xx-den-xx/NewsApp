package com.bda.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "articles")
data class ArticleDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Embedded(prefix = "source-") val source: Source,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: Date,
    @ColumnInfo(name = "content") val content: String,
)

data class Source(
    val id: String,
    val name: String,
)
