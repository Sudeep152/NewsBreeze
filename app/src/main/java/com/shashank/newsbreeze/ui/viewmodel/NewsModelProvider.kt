package com.shashank.newsbreeze.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shashank.newsbreeze.data.repository.NewsRepository

open class NewsViewModelProviderFactory( val newsRepository: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return NewsViewModel(newsRepository) as T
    }
}