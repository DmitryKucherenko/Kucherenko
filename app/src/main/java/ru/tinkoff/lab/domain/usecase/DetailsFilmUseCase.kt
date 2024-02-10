package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.repository.FilmsListRepository

class DetailsFilmUseCase(private val repository: FilmsListRepository) {
    suspend fun operator(id: Int) = repository.getDetailsFilm(id)
}