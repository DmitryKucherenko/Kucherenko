package ru.tinkoff.lab.domain.model


data class FilmsList(
    val pagesCount: Int,
    val films: List<PreviewFilm>
)
