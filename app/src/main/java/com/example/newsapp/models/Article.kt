package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Room veritabanı için Article tablosu oluşturuluyor
@Entity(
    tableName = "articles" // Tablo adı belirtiliyor
)
data class Article(
    @PrimaryKey(autoGenerate = true) // Birincil anahtar ve otomatik olarak artırılacak
    var id: Int? = null,
    val author: String, // Makale yazarı
    val content: String, // Makale içeriği
    val description: String, // Makale açıklaması
    val publishedAt: String, // Makalenin yayınlanma tarihi
    val source: Source, // Makale kaynağı (Source türünde bir nesne)
    val title: String, // Makale başlığı
    val url: String, // Makale URL'si
    val urlToImage: String // Makalenin görsel URL'si
) : Serializable // Serializable arayüzü, nesnenin serileştirilebilmesini sağlar
