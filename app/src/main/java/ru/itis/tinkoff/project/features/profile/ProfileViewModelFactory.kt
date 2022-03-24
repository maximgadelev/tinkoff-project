package ru.itis.tinkoff.project.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.features.profile.viewModel.UserViewModel

class ProfileViewModelFactory(
    private val di: DIContainer,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(UserViewModel::class.java) ->
                UserViewModel(di.getUserUseCase)
                        as? T ?: throw IllegalArgumentException("Unknown ViewModel class")
            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        }
}
