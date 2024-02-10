package ru.tinkoff.lab.presentation.FilmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.model.PreviewFilm
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase

class FilmListViewModel(
    private var filmListUseCase: FilmsListUseCase
) : ViewModel() {


    fun getPreviewFilmsList(): StateFlow<List<PreviewFilm>> =
        flow {
            emit(filmListUseCase())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = listOf()
        )


}