package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    suspend operator fun invoke(film:Film) = favouriteRepository.addToFavourite(film)
}