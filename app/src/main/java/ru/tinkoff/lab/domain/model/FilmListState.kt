package ru.tinkoff.lab.domain.model

sealed class FilmListState {

    object Loading : FilmListState()
    data class Error(
        val error: Throwable
    ) : FilmListState()

    data class Success(
        val filmsList: List<PreviewFilm>
    ) : FilmListState()
}
