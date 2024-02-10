package ru.tinkoff.lab.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmDto(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<CountryDto>,
    val genres: List<GenreDto>,
    val posterUrl: String,
    val posterUrlPreview: String,
)



