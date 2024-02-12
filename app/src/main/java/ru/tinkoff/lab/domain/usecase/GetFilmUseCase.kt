package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class GetFilmUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(idFilm: Int): Film = repository.getFavouriteFilm(idFilm)
}
