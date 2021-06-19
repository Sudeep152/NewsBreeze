package com.shashank.newsbreeze.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shashank.newsbreeze.MainActivity
import com.shashank.newsbreeze.R
import com.shashank.newsbreeze.adapter.NewsArticleAdapter
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.saved_article.*

class SavedFragment :Fragment(R.layout.saved_article) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsArticleAdapter
     var cehckSave:String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("checkSafe")?.let {
            this.cehckSave=it
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity ).viewModel


        setupRV(cehckSave)



        Toast.makeText(context, "$cehckSave ", Toast.LENGTH_SHORT).show()



        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedFragment_to_articleFragment,
                bundle
            )
        }




        viewModel.getsavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })


    }
    private fun setupRV( check :String){
        newsAdapter= NewsArticleAdapter(requireActivity(),check)
        rv.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }


    }
}