package ru.tinkoff.lab.presentation.filmList.previewFilmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.model.FilmListState
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.extenstions.mergeWith

class FilmListViewModel(
    private var filmListUseCase: FilmsListUseCase
) : ViewModel() {

    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow {
        refreshDataEvent.collect {
            filmListUseCase().collect {
                emit(it)
            }
        }
    }

    val loadPreviewFilmsList =
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


}

