package ru.tinkoff.lab.di

import dagger.Module
import dagger.Provides
import ru.tinkoff.lab.domain.usecase.FilmsListUseCase
import ru.tinkoff.lab.presentation.FilmList.FilmListViewModel

@Module
class PresentationModule {

    @Provides
    fun provideFilmListViewModel(
         filmListUseCase: FilmsListUseCase
    ): FilmListViewModel {
        return FilmListViewModel(filmListUseCase)
    }

}