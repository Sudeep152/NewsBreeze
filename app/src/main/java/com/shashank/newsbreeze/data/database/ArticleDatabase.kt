package com.shashank.newsbreeze.data.database

import android.content.Context
import androidx.room.*
import com.shashank.newsbreeze.data.dao.ArticleDao
import com.shashank.newsbreeze.data.entites.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase() {

   abstract fun getDao():ArticleDao

   companion object{
       @Volatile
       private var INSTANCE: ArticleDatabase? = null


       fun getDatabase(
           context: Context
       ): ArticleDatabase {

           return INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                  ArticleDatabase::class.java,
                   "article_database"
               ).build()
               INSTANCE = instance
               // return instance
               instance
           }
       }







   }

}