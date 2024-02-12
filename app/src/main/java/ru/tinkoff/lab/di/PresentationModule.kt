package ru.tinkoff.lab.di

import dagger.Module
import dagger.Provides
import ru.tinkoff.lab.domain.usecase.AddFavouriteUseCase
import ru.tinkoff.lab.domain.usecase.DeleteUseCase
import ru.tinkoff.lab.domain.usecase.FavouriteIdsUseCase
import ru.tinkoff.lab.domain.usecase.FavouriteListUseCase
import ru.tinkoff.lab.domain.usecase.FilmDetailsUseCase
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.presentation.favouriteFilms.filmDetails.FavouriteDetailsViewModel
import ru.tinkoff.lab.presentation.favouriteFilms.filmList.FavouriteListViewModel
import ru.tinkoff.lab.presentation.films.filmDetails.FilmDetailsViewModel
import ru.tinkoff.lab.presentation.films.filmList.FilmListViewModel

@Module
class PresentationModule {

    @Provides
    fun provideFilmListViewModel(
        filmListUseCase: FilmsListUseCase,
        favouriteIdsUseCase: FavouriteIdsUseCase,
        addFavouriteUseCase: AddFavouriteUseCase,
        deleteUseCase: DeleteUseCase
    ): FilmListViewModel {
        return FilmListViewModel(
            filmListUseCase,
            favouriteIdsUseCase,
            addFavouriteUseCase,
            deleteUseCase
        )
    }

    @Provides
    fun provideDetailsFilmViewModel(
        filmDetailsUseCase: FilmDetailsUseCase
    ): FilmDetailsViewModel {
        return FilmDetailsViewModel(filmDetailsUseCase)
    }

    @Provides
    fun provideFavouriteDetailsViewModel(
        filmDetailsUseCase: FilmDetailsUseCase
    ): FavouriteDetailsViewModel {
        return FavouriteDetailsViewModel(filmDetailsUseCase)
    }

    @Provides
    fun provideFavouriteListViewModel(
        favouriteListUseCase: FavouriteListUseCase,
        deleteUseCase: DeleteUseCase
    ): FavouriteListViewModel {
        return FavouriteListViewModel(favouriteListUseCase, deleteUseCase)
    }


}