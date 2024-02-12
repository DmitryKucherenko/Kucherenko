package ru.tinkoff.lab.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.model.DetailsFilmState
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class DetailsFilmUseCase @Inject constructor(private val repository: FilmsListRepository) {
    operator fun invoke(id: Int) = repository.getDetailsFilm(id)
}