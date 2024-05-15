package com.example.newsapp.models

// API yanıtını temsil eden veri sınıfı
data class NewsResponse(
    val articles: List<Article>, // Haber makalelerinin listesi
    val status: String, // API yanıt durumu (örneğin, "ok")
    val totalResults: Int // Toplam haber makalesi sayısı
)
