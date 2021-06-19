package com.shashank.newsbreeze.data.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shashank.newsbreeze.data.dao.ArticleDao
import com.shashank.newsbreeze.data.entites.Article
import com.shashank.newsbreeze.data.entites.Source

@Database(entities = [Article::class],version = 2,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase() {

   abstract fun getDao():ArticleDao

   companion object{
       @Volatile
       private var INSTANCE: ArticleDatabase? = null

       var MIGRATION_1_2: Migration = object : Migration(1, 2) {
           override fun migrate(database: SupportSQLiteDatabase) {
               database.execSQL("Create Table IF NOT EXISTS article_table(id INT PRIMARY KEY NOT NULL ,author TEXT NOT NULL , content TEXT NOT NULL, description TEXT NOT NULL, publishedAt TEXT NOT NULL, source TEXT NOT NULL, title TEXT NOT NULL, url TEXT NOT NULL,  urlToImage TEXT NOT NULL )" )
           }
       }

       fun getDatabase(
           context: Context
       ): ArticleDatabase {

           return INSTANCE ?: synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                  ArticleDatabase::class.java,
                   "article_database"
               ).addMigrations(MIGRATION_1_2)
                   .build()

               INSTANCE = instance
               // return instance
               instance
           }
       }







   }

}