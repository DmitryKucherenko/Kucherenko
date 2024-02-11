package ru.tinkoff.lab.di

import dagger.Module
import dagger.Provides
import ru.tinkoff.lab.domain.usecase.DetailsFilmUseCase
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.presentation.filmList.detailsFilm.DetailsFilmViewModel
import ru.tinkoff.lab.presentation.filmList.previewFilmList.FilmListViewModel

@Module
class PresentationModule {

    @Provides
    fun provideFilmListViewModel(
        filmListUseCase: FilmsListUseCase
    ): FilmListViewModel {
        return FilmListViewModel(filmListUseCase)
    }

    @Provides
    fun provideDetailsFilmViewModel(
        detailsFilmUseCase: DetailsFilmUseCase
    ): DetailsFilmViewModel {
        return DetailsFilmViewModel(detailsFilmUseCase)
    }

}