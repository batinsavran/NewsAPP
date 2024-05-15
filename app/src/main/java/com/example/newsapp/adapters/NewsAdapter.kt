package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article

// Haberleri göstermek için RecyclerView Adapter'ı oluşturuluyor
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // İç sınıf: Her bir haber öğesi için ViewHolder
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // View'ları geçici olarak saklamak için değişkenler
    lateinit var articleImage: ImageView
    lateinit var articleSource: TextView
    lateinit var articleTitle: TextView
    lateinit var articleDescription: TextView
    lateinit var articleDateTime: TextView

    // Farklı liste öğelerini karşılaştırmak için DiffUtil.ItemCallback tanımlanıyor
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        // İki öğenin aynı olup olmadığını kontrol eder
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        // İki öğenin içeriklerinin aynı olup olmadığını kontrol eder
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // AsyncListDiffer ile liste farklılıkları işlenir
    val differ = AsyncListDiffer(this, differCallback)

    // ViewHolder oluşturulurken çağrılır
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            // item_news.xml layout dosyası şişirilir
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    // Liste öğelerinin sayısını döner
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Tıklama olayını dinlemek için bir işlev tanımlanıyor
    private var onItemClickListener: ((Article) -> Unit)? = null

    // ViewHolder bağlanırken çağrılır
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // Mevcut listedeki öğe alınır
        val article = differ.currentList[position]

        // item_news.xml içindeki view'lar bulunur
        articleImage = holder.itemView.findViewById(R.id.articleImage)
        articleSource = holder.itemView.findViewById(R.id.articleSource)
        articleTitle = holder.itemView.findViewById(R.id.articleTitle)
        articleDescription = holder.itemView.findViewById(R.id.articleDescription)
        articleDateTime = holder.itemView.findViewById(R.id.articleDateTime)

        // Görüntü ve metinler view'lara yüklenir
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(articleImage)
            articleSource.text = article.source.name
            articleTitle.text = article.title
            articleDescription.text = article.description
            articleDateTime.text = article.publishedAt

            // Öğeye tıklanma olayını ayarlar
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    // Tıklama olayını ayarlamak için bir işlev
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
