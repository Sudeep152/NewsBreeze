package com.shashank.newsbreeze.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shashank.newsbreeze.MainActivity
import com.shashank.newsbreeze.R
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel

class SavedFragment :Fragment(R.layout.saved_article) {
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =(activity as MainActivity).viewModel
    }
}