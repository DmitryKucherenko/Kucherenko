package ru.tinkoff.lab.presentation.favouriteFilms.filmList


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.tinkoff.lab.domain.model.Film
import ru.tinkoff.lab.domain.state.FilmListState
import ru.tinkoff.lab.domain.usecase.DeleteUseCase
import ru.tinkoff.lab.domain.usecase.FavouriteListUseCase

class FavouriteListViewModel(
    favouriteListUseCase: FavouriteListUseCase,
    var deleteUseCase: DeleteUseCase
) : ViewModel() {


    val loadFilmsList: Flow<List<Film>> = favouriteListUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun deleteFilm(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase(filmId)
        }
    }

}

