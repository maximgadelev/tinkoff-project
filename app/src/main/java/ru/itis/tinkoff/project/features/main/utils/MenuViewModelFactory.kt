package ru.itis.tinkoff.project.features.main.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.features.main.presentation.ui.MainViewModel

class MenuViewModelFactory(
    private val di: DIContainer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(di.menuRepository, di.entityMapper)
                        as? T ?: throw IllegalArgumentException("Unknown ViewModel class")
            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        }
}


