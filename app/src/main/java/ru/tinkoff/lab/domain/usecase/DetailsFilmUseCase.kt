package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class DetailsFilmUseCase @Inject constructor(private val repository: FilmsListRepository) {
    suspend operator fun invoke(id: Int):DetailsFilm = repository.getDetailsFilm(id)
}