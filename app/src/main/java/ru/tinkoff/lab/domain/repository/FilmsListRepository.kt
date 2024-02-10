package ru.tinkoff.lab.domain.repository

import ru.tinkoff.lab.data.network.model.DetailsFilmApi
import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.model.PreviewFilm

interface FilmsListRepository {
    suspend fun getFilmsList():List<PreviewFilm>
    suspend fun getDetailsFilm(id:Int):DetailsFilm
}