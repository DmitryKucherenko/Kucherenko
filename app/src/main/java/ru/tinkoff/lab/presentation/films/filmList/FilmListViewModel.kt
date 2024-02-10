package ru.tinkoff.lab.presentation.films.filmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.state.FilmListState
import ru.tinkoff.lab.domain.usecase.AddFavouriteUseCase
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class FilmListViewModel(
    private var filmListUseCase: FilmsListUseCase,
    private var addFavouriteUseCase: AddFavouriteUseCase
) : ViewModel() {

    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow {
        refreshDataEvent.collect {
            filmListUseCase().collect {
                emit(it)
            }
        }
    }

    val loadFilmsList =
        flow {
            filmListUseCase().collect {
                emit(it)
            }
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


}

