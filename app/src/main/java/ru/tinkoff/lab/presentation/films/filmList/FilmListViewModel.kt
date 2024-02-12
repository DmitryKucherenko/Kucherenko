package ru.tinkoff.lab.presentation.films.filmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.state.FilmListState
import ru.tinkoff.lab.domain.usecase.AddFavouriteUseCase
import ru.tinkoff.lab.domain.usecase.DeleteUseCase
import ru.tinkoff.lab.domain.usecase.FavouriteIdsUseCase
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class FilmListViewModel(
    private var filmListUseCase: FilmsListUseCase,
    favouriteIdsUseCase: FavouriteIdsUseCase,
    private var addFavouriteUseCase: AddFavouriteUseCase,
    private var deleteUseCase: DeleteUseCase
) : ViewModel() {

    private val favouriteIdsList = mutableListOf<Int>()
    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow {
        refreshDataEvent.collect {
            filmListUseCase().collect {
                emit(it)
            }
        }
    }

    val loadFilmsList =
        filmListUseCase().combine(favouriteIdsUseCase()) { films, favouriteIds ->
            favouriteIdsList.clear()
            favouriteIdsList.addAll(favouriteIds)
            films
        }
            .mergeWith(refreshDataFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = FilmListState.Loading
            )

    fun refreshFilmList() {
        viewModelScope.launch {
            refreshDataEvent.emit(Unit)
        }
    }

    fun addFavourite(film: Film) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavouriteUseCase(film)
        }
    }


    fun isFavourite(filmId: Int): Boolean {
        return filmId in favouriteIdsList
    }

    fun deleteFavourite(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase(filmId)
        }

    }


}

