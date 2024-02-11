package ru.tinkoff.lab.presentation.filmList.previewFilmList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.extenstions.mergeWith
import ru.tinkoff.lab.presentation.filmList.detailsFilm.DetailsFilmState

class FilmListViewModel(
    private var filmListUseCase: FilmsListUseCase
) : ViewModel() {

    private val refreshDataEvent = MutableSharedFlow<Unit>()

    private val refreshDataFlow = flow{
        refreshDataEvent.collect{
            emit(
                FilmListState.Films(
                    filmsList = filmListUseCase()
                ) as FilmListState
            )
        }
    }.retry()



    val loadPreviewFilmsList =
        flow {
                emit(filmListUseCase())
        }
            .filter { it.isNotEmpty() }
            .map {
                FilmListState.Films(filmsList = it) as FilmListState
            }
            .onStart {
                emit(FilmListState.Loading)
            }.catch {
                Log.d("TTT","catch err")
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
            Log.d("TTT","refresh")
            refreshDataEvent.emit(Unit)
        }
    }


}

