package ru.tinkoff.lab.di

import android.content.Context
import dagger.BindsInstance

import dagger.Component
import ru.tinkoff.lab.presentation.films.filmList.FilmListFragment
import ru.tinkoff.lab.presentation.MainActivity
import ru.tinkoff.lab.presentation.favouriteFilms.filmDetails.FavouriteDetailsFragment
import ru.tinkoff.lab.presentation.favouriteFilms.filmList.FavouriteListFragment
import ru.tinkoff.lab.presentation.films.filmDetails.FilmDetailsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DataModuleBinds::class,
        ViewModelModule::class,
        PresentationModule::class
    ]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance   context: Context
        ): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(filmListFragment: FilmListFragment)
    fun inject(filmDetailsFragment: FilmDetailsFragment)
    fun inject(favouriteListFragment: FavouriteListFragment)
    fun inject(favouriteDetailsFragment: FavouriteDetailsFragment)

}