package ru.tinkoff.lab.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.tinkoff.lab.data.local.db.FilmDao
import ru.tinkoff.lab.data.mapper.FilmsMapper
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import javax.inject.Inject


class FavouriteRepositoryImpl @Inject constructor(
    private val filmsDao: FilmDao
) : FavouriteRepository {
    override fun getFavouriteFilms(): Flow<List<Film>> =
        filmsDao.getFavouriteFilms().map { filmDbList ->
            FilmsMapper.filmDbListToFilmList(filmDbList)
        }


    override suspend fun search(query: String): Flow<List<Film>> =
        filmsDao.search(query).map { filmDbList ->
            FilmsMapper.filmDbListToFilmList(filmDbList)
        }

    override suspend fun addToFavourite(film: Film) =
        filmsDao.insertFilm(FilmsMapper.filmToFilmDb(film))


    override suspend fun removeFromFavourite(filmId: Int) = filmsDao.deleteFilm(filmId)
    override fun getFavouritesId(): Flow<List<Int>> = filmsDao.getFavouritesId()

    override suspend fun getFavouriteFilm(filmId: Int): Film =
        FilmsMapper.filmDbToFilm(filmsDao.getFilm(filmId))


}