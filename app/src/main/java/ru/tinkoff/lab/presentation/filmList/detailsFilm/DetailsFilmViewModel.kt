package ru.tinkoff.lab.presentation.filmList.detailsFilm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.usecase.DetailsFilmUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class DetailsFilmViewModel(
    private var detailsFilmUseCase: DetailsFilmUseCase
) : ViewModel() {

    private var loadedID: Int = -1

    fun loadDetailsFilm(id: Int): StateFlow<DetailsFilmState> =
        flow {
            loadedID = id
            emit(DetailsFilmState.Films(detailsFilm = detailsFilmUseCase(id)) as DetailsFilmState)
        }
            .onStart {
                emit(DetailsFilmState.Loading)
            }
            .catch {
                emit(DetailsFilmState.Error(error = it))
            }
            .mergeWith(refreshDataFlow)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = DetailsFilmState.Initial
            )


    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow {
        refreshDataEvent.collect {
            emit(
                DetailsFilmState.Films(
                    detailsFilm = detailsFilmUseCase(loadedID)
                )
            )
        }
    }.retry()

    fun refreshDetailsFilm() {
        viewModelScope.launch {
            refreshDataEvent.emit(Unit)
        }
    }


}