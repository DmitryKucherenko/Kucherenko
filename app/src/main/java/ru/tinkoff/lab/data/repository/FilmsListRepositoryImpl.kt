package ru.tinkoff.lab.data.repository

import ru.tinkoff.lab.data.mapper.FilmApiToDbMapper
import ru.tinkoff.lab.data.network.ApiService
import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.model.PreviewFilm
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class FilmsListRepositoryImpl @Inject constructor(
    private val filmApiService: ApiService
) : FilmsListRepository {

    private val apiKey = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    private val type = "TOP_100_POPULAR_FILMS"


    override suspend fun getFilmsList(): List<PreviewFilm> =
        FilmApiToDbMapper.filmListApiToDb(filmApiService.getFilms(apiKey, type).films)


    override suspend fun getDetailsFilm(id: Int): DetailsFilm =
        FilmApiToDbMapper.detailsFilmApiToDb(filmApiService.getDetailsFilm(apiKey, id))
}