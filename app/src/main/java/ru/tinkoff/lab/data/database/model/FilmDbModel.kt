package ru.tinkoff.lab.data.database.model

import androidx.room.Entity
import ru.tinkoff.lab.data.network.model.CountryApi
import ru.tinkoff.lab.data.network.model.GenreApi

@Entity
data class FilmDbModel(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<CountryApi>,
    val genres: List<GenreApi>,
    val posterUrl: String,
    val posterUrlPreview: String,
)