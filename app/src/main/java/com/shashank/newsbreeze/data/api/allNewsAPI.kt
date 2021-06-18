package com.shashank.newsbreeze.data.api

import com.shashank.newsbreeze.data.entites.NewsResponse
import com.shashank.newsbreeze.someUtils.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface allNewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String ="in",
        @Query("page")
        pageNumber :Int =1,
        @Query("apiKey")
        apiKey :String =API_KEY
    ):Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        page:Int = 1,
        @Query("apiKey")
        apikey: String= API_KEY

    ):Response<NewsResponse>

}