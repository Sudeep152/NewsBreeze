package com.shashank.newsbreeze.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shashank.newsbreeze.MainActivity
import com.shashank.newsbreeze.R
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.frament_article.*
import kotlinx.android.synthetic.main.single_article.view.*
import java.io.Serializable

class ArticleFragment : Fragment(R.layout.frament_article) {
 lateinit var viewModel: NewsViewModel

    val args:ArticleFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window=  requireActivity().window
        viewModel = (activity as MainActivity).viewModel
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.purple_500)
        window.navigationBarColor=ContextCompat.getColor(requireContext(), R.color.black)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)



        val article =args.article




        web.apply {

            webViewClient = WebViewClient()
            loadUrl(article.url)

        }

        author.apply {

            this.text= article.author
        }
        Glide.with(this).load(article.urlToImage).into(imgAr)

        saveBtn1.setOnClickListener {
            viewModel.saveArticle(article)
            Toast.makeText(context, "your news saved", Toast.LENGTH_SHORT).show()
        }


    }


}