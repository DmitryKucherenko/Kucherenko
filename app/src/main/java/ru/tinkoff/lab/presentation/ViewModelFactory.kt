package com.example.vkclientnews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider



class ViewModelFactory (
    private val viewModels: @JvmSuppressWildcards Map<String, ViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass.simpleName] as T
    }
}