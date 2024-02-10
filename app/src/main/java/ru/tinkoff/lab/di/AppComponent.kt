package ru.tinkoff.lab.di

import android.content.Context
import dagger.BindsInstance

import dagger.Component
import ru.tinkoff.lab.presentation.FilmList.FilmList
import ru.tinkoff.lab.presentation.MainActivity
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
    fun inject(filmList: FilmList)

}