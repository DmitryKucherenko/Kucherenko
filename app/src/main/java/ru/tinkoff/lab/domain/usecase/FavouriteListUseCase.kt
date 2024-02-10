package ru.tinkoff.lab.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteListUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(): Flow<List<Film>> = repository.getFavouriteFilms()
}