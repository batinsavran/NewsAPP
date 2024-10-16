package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentFavoritesBinding
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar

// FavoritesFragment: Favori haber makalelerini gösteren fragment
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    // ViewModel'i almak için değişken
    private lateinit var newsViewModel: NewsViewModel
    // Haberler adaptörü
    private lateinit var newsAdapter: NewsAdapter
    // View binding: Görünüm öğelerini bağlamak için
    private lateinit var binding: FragmentFavoritesBinding

    // Fragment'in view'ı oluşturulduğunda çağrılır
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // View binding ile görünüm öğelerini bağla
        binding = FragmentFavoritesBinding.bind(view)

        // ViewModel'i, ana aktiviteden al
        newsViewModel = (activity as NewsActivity).newsViewModel
        // Favori haberler RecyclerView'ini ayarla
        setupFavouritesRecycler()

        // Haberlere tıklama olayı
        newsAdapter.setOnItemClickListener {
            // Seçilen makaleyi ArticleFragment'e göndermek için Navigation Component kullan
            val bundle = Bundle().apply {
                putSerializable("defaultArt", it)
            }
            findNavController().navigate(R.id.action_favoritesFragment_to_articleFragment, bundle)
        }

        // RecyclerView üzerinde kaydırma işlemi için ItemTouchHelper oluştur
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, // Yön (yukarı-aşağı)
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // Yön (sol-sağ)
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true // Sürükleme hareketini destekle
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                // Makaleyi favorilerden sil
                //newsViewModel.deleteArticle(article)
                // Silme işlemi başarılıysa kullanıcıya bilgi ver
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    // Geri alma işlemi için Action oluştur
                    setAction("Undo") {
                        newsViewModel.addToFavourites(article)
                    }
                    // Snackbar'ı göster
                    show()
                }
            }
        }
        // RecyclerView'e ItemTouchHelper uygula
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerFavourites)
        }

        // Favori haberleri izle ve RecyclerView'e ekle
        newsViewModel.getFavouritesNews().observe(viewLifecycleOwner) { articles ->
            newsAdapter.differ.submitList(articles)
        }
    }

    // Favori haberler RecyclerView'ini ayarla
    private fun setupFavouritesRecycler() {
        // Haberler adaptörünü oluştur
        newsAdapter = NewsAdapter()
        // RecyclerView için düzen belirle
        binding.recyclerFavourites.apply {
            adapter = newsAdapter // Adaptörü ayarla
            layoutManager = LinearLayoutManager(activity) // Yatay düzeni ayarla
        }
    }
}
