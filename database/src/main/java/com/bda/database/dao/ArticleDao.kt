package com.bda.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.bda.database.models.ArticleDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getAll(): List<ArticleDBO>

    @Query("SELECT * FROM articles")
    fun  observeAll(): Flow<List<ArticleDBO>>

    @Insert
    suspend fun insert(articles: List<ArticleDBO>)

    @Delete
    suspend fun delete(articles: List<ArticleDBO>)

    @Query("DELETE FROM articles")
    suspend fun clean()
}