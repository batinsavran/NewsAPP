package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.Article

// Data Access Object (DAO) arayüzü, veritabanı işlemlerini tanımlar
@Dao
interface ArticleDAO {

    // Veritabanına yeni bir makale ekler veya var olan bir makaleyi günceller
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long
    // onConflict = OnConflictStrategy.REPLACE: Çakışma durumunda var olan kaydı yeni kayıttan gelen bilgilerle değiştirir

    // Tüm makaleleri sorgular ve canlı veri (LiveData) olarak döner
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    // Veritabanından bir makaleyi siler
    @Delete
    suspend fun deleteArticle(article: Article)
}
