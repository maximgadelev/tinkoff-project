package ru.itis.tinkoff.project.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.Profile
import ru.itis.tinkoff.project.features.common.Event

class UserViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private var _user = MutableSharedFlow<Profile>(replay = 1, onBufferOverflow = DROP_OLDEST)
    val user: SharedFlow<Profile> = _user.asSharedFlow()
    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    fun onViewCreated() {
        viewModelScope.launch {
            try {
                val user = userRepository.getUser()
                _user.emit(user)
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
