package ru.tinkoff.lab.domain.state

import ru.tinkoff.lab.domain.model.Film

sealed class FilmListState {

    object Loading : FilmListState()
    data class Error(
        val error: Throwable
    ) : FilmListState()

    data class Success(
        val filmsList: List<Film>
    ) : FilmListState()
}
