package com.example.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository

// NewsActivity: Ana aktivite, fragmentleri yönetir
class NewsActivity : AppCompatActivity() {

    // ViewModel değişkeni
    lateinit var newsViewModel: NewsViewModel
    // View binding: Görünüm öğelerini bağlamak için
    private lateinit var binding: ActivityNewsBinding

    // Aktivite oluşturulduğunda çağrılır
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View binding ile görünüm öğelerini bağla
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Veritabanı erişimi için NewsRepository oluştur
        val newsRepository = NewsRepository(ArticleDatabase(this))
        // ViewModel ve bağımlılıklarını sağlayan ViewModelProviderFactory oluştur
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        // ViewModel'i al
        newsViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        // Navigation Component için NavHostFragment'i al
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController // NavHostFragment'in NavController'ını al

        // BottomNavigationView'i NavController ile bağla
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
