package com.shashank.newsbreeze.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shashank.newsbreeze.MainActivity
import com.shashank.newsbreeze.R
import com.shashank.newsbreeze.data.entites.Article
import com.shashank.newsbreeze.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.single_article.view.*

class NewsArticleAdapter(var require: FragmentActivity,var  check: String) :RecyclerView.Adapter<NewsArticleAdapter.NewsViewHolder>() {



    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)


    inner  class  NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.single_article,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val article =differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(urltoImg)
            txtTitle.text = article.title
            txtdescription.text= article.description
            txtpublishdate.text= article.publishedAt

                setOnClickListener {
                    onItemClickListener?.let {it(article)

                    }
                }

            if(check == "TRUE"){
                saveBtn.visibility=View.GONE
            }
           readBtn.setOnClickListener {
                onItemClickListener?.let {it(article)

                }
            }

          
             saveBtn.setOnClickListener {
                var viewModel: NewsViewModel = (require as MainActivity).viewModel

                 viewModel.saveArticle(article)
                 Toast.makeText(context, "your news saved", Toast.LENGTH_SHORT).show()


             }



        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? =null


    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }



}