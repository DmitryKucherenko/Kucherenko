package ru.tinkoff.lab.presentation.favouriteFilmList

import ru.tinkoff.lab.domain.model.PreviewFilm

sealed class FilmListState {
    object Initial : FilmListState()
    object Loading : FilmListState()
    data class Error(
        val error: Throwable
    ) : FilmListState()

    data class Films(
        val filmsList: List<PreviewFilm>
    ) : FilmListState()
}
