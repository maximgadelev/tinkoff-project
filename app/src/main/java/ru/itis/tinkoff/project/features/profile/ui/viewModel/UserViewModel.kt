package ru.itis.tinkoff.project.features.profile.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository

class UserViewModel(
    private val userRepository: UserRepository,
    private val userIdRepository: UserIdRepository,
) : ViewModel() {
    private var _user = MutableStateFlow<User>(initializeEmptyUser())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        viewModelScope.launch {
            val user = userRepository.getUser(userIdRepository.getUserId())
            _user.value = user
        }
    }

    private fun initializeEmptyUser() = User(0, "", "", "", "", "", null)
}
