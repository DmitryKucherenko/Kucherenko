package ru.tinkoff.lab.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmsListApi(
    val pagesCount: Int,
    val films: List<PreviewFilmApi>
)
