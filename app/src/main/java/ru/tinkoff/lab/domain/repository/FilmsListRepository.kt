package ru.tinkoff.lab.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.model.DetailsFilmState
import ru.tinkoff.lab.domain.model.FilmListState

interface FilmsListRepository {
    fun getFilmsList(): Flow<FilmListState>
    fun getDetailsFilm(id:Int): Flow<DetailsFilmState>
}