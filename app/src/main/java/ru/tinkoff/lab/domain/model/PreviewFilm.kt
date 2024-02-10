package ru.tinkoff.lab.domain.model




data class PreviewFilm(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val posterUrl: String,
    val posterUrlPreview: String,
)



