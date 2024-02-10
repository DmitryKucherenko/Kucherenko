package ru.tinkoff.lab.data.local.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmDetailsDb(
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val description: String,
    val shortDescription:String,
    val countries: List<String>,
    val genres: List<String>
)