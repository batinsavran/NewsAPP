package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// Veritabanı sınıfı: Room veritabanı tanımlanıyor
@Database(
    entities = [Article::class], // Veritabanında depolanacak tablo
    version = 1 // Veritabanı sürümü
)
@TypeConverters(Converters::class) // Tür dönüştürücüler: Veritabanı işlemleri için dönüştürücü sınıfı
abstract class ArticleDatabase : RoomDatabase() {

    // ArticleDAO'yu almak için soyut bir işlev
    abstract fun getArticleDao(): ArticleDAO

    companion object {
        // Volatile: instance değişkeni, farklı thread'lerdeki değişikliklerin hemen görünür olmasını sağlar
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        // invoke operatörü: ArticleDatabase'in tek bir örneğini (singleton) oluşturur veya döner
        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        // Veritabanı oluşturma işlevi
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java, // Veritabanı sınıfı
                "article_db.db" // Veritabanı dosya adı
            ).build() // Veritabanı oluşturulur ve döner
    }
}
