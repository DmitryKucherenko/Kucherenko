package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class FilmDetailsUseCase @Inject constructor(
    private val repository: FilmsListRepository
) {
    operator fun invoke(id: Int) = repository.getDetailsFilm(id)
}