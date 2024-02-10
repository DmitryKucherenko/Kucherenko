package ru.tinkoff.lab.data.network.model

import com.squareup.moshi.JsonClass
import ru.tinkoff.lab.domain.model.Country

@JsonClass(generateAdapter = true)
data class PreviewFilmApi(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<CountryApi>,
    val genres: List<GenreApi>,
    val posterUrl: String,
    val posterUrlPreview: String,
)



