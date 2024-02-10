package ru.tinkoff.lab.domain.model


data class FilmDetails(
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val description: String,
    val shortDescription:String,
    val countries: List<String>,
    val genres: List<String>
)