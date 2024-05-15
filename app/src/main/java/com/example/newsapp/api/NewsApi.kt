package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Haber API'si ile iletişim kurmak için kullanılan Retrofit arayüzü
interface NewsApi {

    // Üst haber başlıklarını almak için GET isteği
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String = "us", // Ülke kodu, varsayılan olarak "us" (Amerika Birleşik Devletleri)
        @Query("page")
        pageNumber: Int = 1, // Sayfa numarası, varsayılan olarak 1
        @Query("apiKey")
        apiKey: String = API_KEY // API anahtarı, sabitler dosyasından alınır
    ): Response<NewsResponse> // Yanıt, NewsResponse türünde bir Response nesnesi olarak döner

    // Haber aramak için GET isteği
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String, // Arama sorgusu
        @Query("page")
        pageNumber: Int = 1, // Sayfa numarası, varsayılan olarak 1
        @Query("apiKey")
        apiKey: String = API_KEY // API anahtarı, sabitler dosyasından alınır
    ): Response<NewsResponse> // Yanıt, NewsResponse türünde bir Response nesnesi olarak döner
}
