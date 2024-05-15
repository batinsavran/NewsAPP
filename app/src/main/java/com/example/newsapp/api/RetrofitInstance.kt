package com.example.newsapp.api

import com.example.newsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance sınıfı, Retrofit nesnesini oluşturur ve NewsApi arayüzü ile iletişimi sağlar
class RetrofitInstance {
    companion object {
        // Retrofit nesnesini tek bir örnek (singleton) olarak oluşturur
        private val retrofit by lazy {
            // HTTP isteklerini ve yanıtlarını loglamak için interceptor oluşturuluyor
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) // Log seviyesini BODY olarak ayarlar
            val client = OkHttpClient.Builder()
                .addInterceptor(logging) // Interceptor'u HTTP client'a ekler
                .build()

            // Retrofit nesnesini oluşturur
            Retrofit.Builder()
                .baseUrl(BASE_URL) // API'nin temel URL'si
                .addConverterFactory(GsonConverterFactory.create()) // Gson dönüştürücü ekleniyor
                .client(client) // HTTP client ekleniyor
                .build()
        }
        // NewsApi arayüzünü oluşturur
        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}
