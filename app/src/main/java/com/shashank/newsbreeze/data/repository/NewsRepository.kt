package com.shashank.newsbreeze.data.repository

import com.shashank.newsbreeze.data.database.ArticleDatabase
import com.shashank.newsbreeze.data.instance.RetrofitInstance

class  NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode :String ,pageNumber:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int)=
        RetrofitInstance.api.searchNews(searchQuery,pageNumber)

}