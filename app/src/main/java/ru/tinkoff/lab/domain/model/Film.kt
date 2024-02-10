package ru.tinkoff.lab.domain.model




data class Film(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<String>,
    val genres: List<String>,
    val posterUrl: String,
    val posterUrlPreview: String,
)



