package ru.tinkoff.lab.domain.state

import ru.tinkoff.lab.domain.model.FilmDetails

sealed class FilmDetailsState {
    object Loading : FilmDetailsState()
    data class Error(
        val error: Throwable
    ) : FilmDetailsState()

    data class Success(
        val filmDetails: FilmDetails
    ) : FilmDetailsState()
}
