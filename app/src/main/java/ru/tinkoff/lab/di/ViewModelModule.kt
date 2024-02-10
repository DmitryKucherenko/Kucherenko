package ru.tinkoff.lab.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import ru.tinkoff.lab.presentation.ViewModelFactory
import ru.tinkoff.lab.presentation.favouriteFilms.filmDetails.FavouriteDetailsViewModel
import ru.tinkoff.lab.presentation.favouriteFilms.filmList.FavouriteListViewModel
import ru.tinkoff.lab.presentation.films.filmDetails.FilmDetailsViewModel
import ru.tinkoff.lab.presentation.films.filmList.FilmListViewModel

@Module
interface ViewModelModule {
    @IntoMap
    @StringKey("FilmListViewModel")
    @Binds
    fun bindListViewModel(impl: FilmListViewModel): ViewModel

    @IntoMap
    @StringKey("FilmDetailsViewModel")
    @Binds
    fun bindDetailsFilmViewModel(impl: FilmDetailsViewModel): ViewModel

    @IntoMap
    @StringKey("FavouriteListViewModel")
    @Binds
    fun bindFavouriteListViewModel(impl: FavouriteListViewModel): ViewModel


    @IntoMap
    @StringKey("FavouriteDetailsViewModel")
    @Binds
    fun bindFavouriteDetailsViewModel(impl: FavouriteDetailsViewModel): ViewModel


    companion object {
        @Provides
        fun provideViewModelFactory(
            viewModels: @JvmSuppressWildcards Map<String, ViewModel>
        ): ViewModelFactory {
            return ViewModelFactory(viewModels)
        }
    }
}