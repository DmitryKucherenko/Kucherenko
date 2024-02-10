package ru.tinkoff.lab.di

import dagger.Binds
import dagger.Module
import ru.tinkoff.lab.data.repository.FavouriteRepositoryImpl
import ru.tinkoff.lab.data.repository.FilmsRepositoryImpl
import ru.tinkoff.lab.domain.repository.FavouriteRepository
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Singleton

@Module
interface DataModuleBinds {
    @Singleton
    @Binds
    fun bindFilmsListRepository(
        filmsRepositoryImpl: FilmsRepositoryImpl
    ): FilmsListRepository

    @Singleton
    @Binds
    fun bindFavouriteRepository(
        favouriteRepository: FavouriteRepositoryImpl
    ): FavouriteRepository

}