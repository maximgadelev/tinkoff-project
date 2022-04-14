package ru.itis.tinkoff.project.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository

class UserViewModel(
    private val userRepository: UserRepository,
    private val userIdRepository: UserIdRepository,
) : ViewModel() {
    private var _user = MutableSharedFlow<User>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val user: SharedFlow<User> = _user.asSharedFlow()

    init {
        viewModelScope.launch {
            val user = userRepository.getUser(userIdRepository.getUserId())
            _user.emit(user)
        }
    }
}
