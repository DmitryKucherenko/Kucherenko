package ru.tinkoff.lab.di

import androidx.lifecycle.ViewModel
import com.example.vkclientnews.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import ru.tinkoff.lab.presentation.FilmList.FilmListViewModel

@Module
interface ViewModelModule {
    @IntoMap
    @StringKey("FilmListViewModel")
    @Binds
    fun bindListViewModel(impl: FilmListViewModel): ViewModel

    companion object{
        @Provides
        fun provideViewModelFactory(
            viewModels: @JvmSuppressWildcards Map<String, ViewModel>
        ): ViewModelFactory {
            return ViewModelFactory(viewModels)
        }
    }
}