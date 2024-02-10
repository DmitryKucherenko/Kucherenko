package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteDetailsUseCase  @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(filmId:Int) = repository.getFavouriteFilm(filmId)
}