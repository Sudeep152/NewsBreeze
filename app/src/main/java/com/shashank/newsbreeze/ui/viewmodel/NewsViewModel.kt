package com.shashank.newsbreeze.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.newsbreeze.Util.Resource
import com.shashank.newsbreeze.data.entites.NewsResponse
import com.shashank.newsbreeze.data.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository):ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage =1


    val searchNews :MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewspage =1


    init {
        getBreakingNews("in")
    }

    fun getBreakingNews(countryCode :String)=
        viewModelScope.launch {
            breakingNews.postValue(Resource.Loading())
            val response =newsRepository.getBreakingNews(countryCode,breakingNewsPage)

            breakingNews.postValue(handlerBreakingNewsResponse(response))
        }

    private  fun handlerBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }
    fun searchNews(searchQuery: String)=
        viewModelScope.launch {
            searchNews.postValue(Resource.Loading())
            val response =newsRepository.searchNews(searchQuery,searchNewspage)

            searchNews.postValue(handlerSearchingNewsResponse(response))

        }

    private  fun handlerSearchingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }
}