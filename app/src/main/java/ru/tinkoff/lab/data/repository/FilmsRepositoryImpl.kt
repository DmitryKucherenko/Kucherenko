package ru.tinkoff.lab.data.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.tinkoff.lab.data.mapper.FilmsMapper
import ru.tinkoff.lab.data.network.api.ApiService
import ru.tinkoff.lab.domain.state.FilmDetailsState
import ru.tinkoff.lab.domain.state.FilmListState
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val filmApiService: ApiService
) : FilmsListRepository {

    private val apiKey = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    private val type = "TOP_100_POPULAR_FILMS"


    override fun getFilmsList() = flow {
        emit(
            FilmsMapper.filmListDtoToFilmList(filmApiService.getFilms(apiKey, type).films)
        )
    }
        .filter { it.isNotEmpty() }
        .map { FilmListState.Success(filmsList = it) as FilmListState }
        .onStart {
            emit(FilmListState.Loading)
        }
        .catch {
            emit(FilmListState.Error(error = it))
        }

    override fun getDetailsFilm(id: Int) =
        flow {
            emit(FilmsMapper.filmDetailsDtoToFilmDetails(filmApiService.getFilmDetails(apiKey, id)))
        }
            .map { FilmDetailsState.Success(filmDetails = it) as FilmDetailsState }
            .onStart {
                emit(FilmDetailsState.Loading)
            }
            .catch {
                emit(FilmDetailsState.Error(error = it))
            }


}