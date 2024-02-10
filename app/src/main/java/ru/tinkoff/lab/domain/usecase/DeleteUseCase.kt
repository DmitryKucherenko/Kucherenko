package ru.tinkoff.lab.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(idFilm: Int) = repository.removeFromFavourite(idFilm)
}