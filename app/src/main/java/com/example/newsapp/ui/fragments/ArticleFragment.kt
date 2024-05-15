package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

// ArticleFragment: Belirli bir haber makalesini gösteren fragment
class ArticleFragment : Fragment(R.layout.fragment_article) {

    // ViewModel'i almak için değişken
    private lateinit var newsViewModel: NewsViewModel
    // NavArgs: Navigation Component ile geçilen argümanları almak için
    private val args: ArticleFragmentArgs by navArgs()
    // View binding: Görünüm öğelerini bağlamak için
    private lateinit var binding: FragmentArticleBinding

    // Fragment'in view'ı oluşturulduğunda çağrılır
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // View binding ile görünüm öğelerini bağlama
        binding = FragmentArticleBinding.bind(view)

        // ViewModel'i, ana aktiviteden al
        newsViewModel = (activity as NewsActivity).newsViewModel
        // Navigation Component ile geçilen makale argümanını al
        val article = args.defaultArt

        // WebView ayarları ve URL yükleme
        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) } // Makale URL'sini yükle
        }

        // FloatingActionButton (favorilere ekleme butonu) tıklama olayı
        binding.fab.setOnClickListener {
            newsViewModel.addToFavourites(article) // Makaleyi favorilere ekle
            Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_SHORT).show() // Snackbar ile kullanıcıya bilgi ver
        }
    }
}
