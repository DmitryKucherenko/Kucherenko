package ru.tinkoff.lab.domain.model


data class DetailsFilm(
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val description: String,
    val shortDescription:String,
    val countries: List<Country>,
    val genres: List<Genre>
)