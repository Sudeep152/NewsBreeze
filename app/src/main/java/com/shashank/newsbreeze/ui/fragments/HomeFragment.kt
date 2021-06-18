package com.shashank.newsbreeze.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shashank.newsbreeze.MainActivity
import com.shashank.newsbreeze.R
import com.shashank.newsbreeze.Util.Resource
import com.shashank.newsbreeze.adapter.NewsArticleAdapter
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.home_articles.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment :Fragment(R.layout.home_articles) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        setupRV()
        gotoSaveSection.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_savedFragment)
        }


        var job : Job?=null
        etSearch.addTextChangedListener { editable->

            job?.cancel()
            job = MainScope().launch {
                delay(300L)

                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }

        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{

                    response.data?.let {  newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message->
                        Log.e("BREAK","Somthingwnet : $message")
                    }
                }
                is Resource.Loading ->{

                }
            }

        })





        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{

                    response.data?.let {  newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message->
                        Log.e("BREAK","Somthingwnet : $message")
                    }
                }
                is Resource.Loading ->{

                }
            }

        })

    }


    private fun setupRV(){
        newsAdapter= NewsArticleAdapter()
        rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }





}