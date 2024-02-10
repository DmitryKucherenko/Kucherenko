package ru.snowadv.kinopoiskfeaturedmovies.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Arrays


class DatabaseTypeConverter {
    @TypeConverter
    fun listToString(list: List<String>):String = list.joinToString() ?: ""

    @TypeConverter
    fun stringToList(string: String):List<String> = string.split(",")

}