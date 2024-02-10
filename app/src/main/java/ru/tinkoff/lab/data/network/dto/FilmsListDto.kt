package ru.tinkoff.lab.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmsListDto(
    val pagesCount: Int,
    val films: List<FilmDto>
)
