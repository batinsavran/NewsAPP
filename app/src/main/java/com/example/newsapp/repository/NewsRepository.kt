package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import retrofit2.Response

// Haber verilerini yönetmek için Repository sınıfı
class NewsRepository(val db: ArticleDatabase) {

    // API'den manşet haberleri almak için fonksiyon
    suspend fun getHeadlines(country: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(country, pageNumber)

    // API'den arama sorgusuna göre haberleri almak için fonksiyon
    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    // Veritabanına makale eklemek veya güncellemek için fonksiyon
    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    // Veritabanından favori haberleri almak için fonksiyon (LiveData döner)
    fun getFavouriteNews() = db.getArticleDao().getAllArticles()

    // Veritabanından makale silmek için fonksiyon
    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

    fun searchNews(searchQuery: String, searchNewsPage: Int): Response<NewsResponse> {
        TODO("Not yet implemented")
    }
}
