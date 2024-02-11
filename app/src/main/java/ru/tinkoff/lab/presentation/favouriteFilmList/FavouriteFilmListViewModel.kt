package ru.tinkoff.lab.presentation.favouriteFilmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class FavouriteFilmListViewModel(
    private var filmListUseCase: FilmsListUseCase
) : ViewModel() {

    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow{
        refreshDataEvent.collect{
            emit(FilmListState.Films(
                filmsList = filmListUseCase()
            ))
        }
    }

    val loadPreviewFilmsList: StateFlow<FilmListState> =
        flow {
                emit(filmListUseCase())
        }
            .filter { it.isNotEmpty() }
            .map {
                FilmListState.Films(filmsList = it) as FilmListState
            }
            .onStart {
                emit(FilmListState.Loading)
            }
            .catch {
                emit(FilmListState.Error(error = it))
            }
            .mergeWith(refreshDataFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = FilmListState.Initial
            )
    fun refreshFilmList(){
        viewModelScope.launch {
            refreshDataEvent.emit(Unit)
        }
    }


}

