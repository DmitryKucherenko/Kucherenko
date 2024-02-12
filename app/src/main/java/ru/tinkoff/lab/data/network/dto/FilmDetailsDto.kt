package ru.tinkoff.lab.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmDetailsDto(
    val kinopoiskId: Int,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val description: String?,
    val shortDescription:String?,
    val countries: List<CountryDto>?,
    val genres: List<GenreDto>?
)