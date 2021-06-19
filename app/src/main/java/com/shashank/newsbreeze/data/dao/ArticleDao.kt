package com.shashank.newsbreeze.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shashank.newsbreeze.data.entites.Article

@Dao
abstract interface ArticleDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long?

    @Query("SELECT * FROM article_table ")
     fun getArticle():LiveData<List<Article>>




}