package ru.tinkoff.lab.domain.model

sealed class DetailsFilmState {
    object Loading : DetailsFilmState()
    data class Error(
        val error: Throwable
    ) : DetailsFilmState()

    data class Success(
        val detailsFilm: DetailsFilm
    ) : DetailsFilmState()
}
