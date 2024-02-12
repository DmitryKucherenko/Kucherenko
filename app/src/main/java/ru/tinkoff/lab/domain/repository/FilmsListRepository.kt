package ru.tinkoff.lab.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.state.FilmDetailsState
import ru.tinkoff.lab.domain.state.FilmListState

interface FilmsListRepository {
    fun getFilmsList(): Flow<FilmListState>
    fun getDetailsFilm(id:Int): Flow<FilmDetailsState>

}