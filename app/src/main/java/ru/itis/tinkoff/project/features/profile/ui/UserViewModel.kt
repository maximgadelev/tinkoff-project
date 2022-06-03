package ru.itis.tinkoff.project.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.Profile

class UserViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private var _user = MutableSharedFlow<Profile>(replay = 1, onBufferOverflow = DROP_OLDEST)
    val user: SharedFlow<Profile> = _user.asSharedFlow()

    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            _user.emit(user)
        }
    }
}
