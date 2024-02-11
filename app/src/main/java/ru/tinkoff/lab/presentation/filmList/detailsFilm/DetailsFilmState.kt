package ru.tinkoff.lab.presentation.filmList.detailsFilm

import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.model.PreviewFilm

sealed class DetailsFilmState {
    object Initial : DetailsFilmState()
    object Loading : DetailsFilmState()
    data class Error(
        val error: Throwable
    ) : DetailsFilmState()

    data class Films(
        val detailsFilm: DetailsFilm
    ) : DetailsFilmState()
}
