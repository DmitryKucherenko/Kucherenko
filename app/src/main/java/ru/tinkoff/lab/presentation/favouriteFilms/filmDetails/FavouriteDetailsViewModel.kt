package ru.tinkoff.lab.presentation.favouriteFilms.filmDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.state.FilmDetailsState
import ru.tinkoff.lab.domain.usecase.FilmDetailsUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class FavouriteDetailsViewModel(
    private var filmDetailsUseCase: FilmDetailsUseCase
) : ViewModel() {

    private var loadedID: Int = -1

    fun loadDetailsFilm(id: Int) =
        flow {
            loadedID = id
            filmDetailsUseCase(id).collect {
                emit(it)
            }
        }
            .mergeWith(refreshDataFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = FilmDetailsState.Loading
            )


    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow {
        refreshDataEvent.collect {
            filmDetailsUseCase(loadedID).collect {
                emit(it)
            }
        }
    }

    fun refreshDetailsFilm() {
        viewModelScope.launch {
            refreshDataEvent.emit(Unit)
        }
    }


}