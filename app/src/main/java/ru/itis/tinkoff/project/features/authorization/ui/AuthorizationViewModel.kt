package ru.itis.tinkoff.project.features.authorization.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.data.repository.TokenRepository
import ru.itis.tinkoff.project.features.common.Event

class AuthorizationViewModel(
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()
    fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            try {
                tokenRepository.loginAndGetToken(login, password)
                eventChannel.send(Event.NavigateToMenuEvent)
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
