package ru.tinkoff.lab.di

import dagger.Binds
import dagger.Module
import ru.tinkoff.lab.data.repository.FilmsListRepositoryImpl
import ru.tinkoff.lab.domain.repository.FilmsListRepository
import javax.inject.Singleton

@Module
interface DataModuleBinds {
    @Singleton
    @Binds
    fun bindFilmsListRepository(
        filmsListRepositoryImpl: FilmsListRepositoryImpl
    ): FilmsListRepository
}