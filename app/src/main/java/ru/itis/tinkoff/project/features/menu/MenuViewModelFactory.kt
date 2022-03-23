package ru.itis.tinkoff.project.features.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.features.menu.ui.MenuViewModel

class MenuViewModelFactory (private val di: DIContainer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MenuViewModel::class.java) ->
                MenuViewModel(di.menuRepository)
                        as? T ?: throw IllegalArgumentException("Unknown ViewModel class")
            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        }
}
