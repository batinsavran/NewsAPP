package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import retrofit2.Response

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getHeadlines(country: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(country, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getFavouriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

    fun searchNews(searchQuery: String, searchNewsPage: Int): Response<NewsResponse> {
        TODO("Not yet implemented")
    }
}
