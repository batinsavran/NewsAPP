package com.example.newsapp.db

import androidx.room.TypeConverter
import com.example.newsapp.models.Source

// Tür dönüştürücüler: Room veritabanı işlemleri için özel türleri dönüştürmek için kullanılır
class Converters {

    // Source nesnesini String'e dönüştürür (Kaynağın adı)
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    // String'i Source nesnesine dönüştürür (Kaynağın adı)
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}
