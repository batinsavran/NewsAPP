package com.example.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.repository.NewsRepository

// ViewModelProvider.Factory'nin özel uygulaması
class NewsViewModelProviderFactory(val app: Application, val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {

    // ViewModel oluşturma işlevi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // NewsViewModel'i oluştururken uygulama ve haber deposunu (repository) sağla
        return NewsViewModel(app, newsRepository) as T
    }
}
