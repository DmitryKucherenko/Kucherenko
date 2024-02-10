package ru.tinkoff.lab.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.snowadv.kinopoiskfeaturedmovies.data.converter.DatabaseTypeConverter

@Entity(tableName = "favouritefilms")
@TypeConverters(DatabaseTypeConverter::class)
data class FilmDb(
    @PrimaryKey val filmId: Int,
    val nameRu: String,
    val year: String,
    val countries: List<String>,
    val genres: List<String>,
    val posterUrl: String,
    val posterUrlPreview: String,
)



