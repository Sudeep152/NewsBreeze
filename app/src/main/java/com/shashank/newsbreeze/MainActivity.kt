package com.shashank.newsbreeze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shashank.newsbreeze.data.database.ArticleDatabase
import com.shashank.newsbreeze.data.repository.NewsRepository
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val newsrepository = NewsRepository(ArticleDatabase.getDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsrepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

    }
}