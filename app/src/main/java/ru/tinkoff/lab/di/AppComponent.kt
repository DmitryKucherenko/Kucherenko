package ru.tinkoff.lab.di

import android.content.Context
import dagger.BindsInstance

import dagger.Component
import ru.tinkoff.lab.presentation.filmList.previewFilmList.FilmListFragment
import ru.tinkoff.lab.presentation.MainActivity
import ru.tinkoff.lab.presentation.filmList.detailsFilm.DetailsFilmFragment
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
            @BindsInstance context: Context
        ): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(filmListFragment: FilmListFragment)
    fun inject(detailsFilmFragment: DetailsFilmFragment)

}