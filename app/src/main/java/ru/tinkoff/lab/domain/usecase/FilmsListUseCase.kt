package ru.tinkoff.lab.domain.usecase

import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class FilmsListUseCase @Inject constructor(private val repository: FilmsListRepository) {
    suspend operator fun invoke() = repository.getFilmsList()
}
