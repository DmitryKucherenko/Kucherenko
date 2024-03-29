package ru.tinkoff.lab.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.model.Film

interface FavouriteRepository {
    fun getFavouriteFilms(): Flow<List<Film>>
    suspend fun getFavouriteFilm(filmId: Int): Film
    suspend fun addToFavourite(film: Film)
    suspend fun removeFromFavourite(filmId: Int)
    fun getFavouritesId():Flow<List<Int>>
    suspend fun search(query: String): Flow<List<Film>>


}