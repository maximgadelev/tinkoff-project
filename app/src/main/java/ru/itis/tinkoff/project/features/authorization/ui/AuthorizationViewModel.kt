package ru.itis.tinkoff.project.features.authorization.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.data.repository.TokenRepository
import ru.itis.tinkoff.project.features.common.ErrorEvent

class AuthorizationViewModel(
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val eventChannel = Channel<ErrorEvent>()
    val eventFlow = eventChannel.receiveAsFlow()
    fun loginUser(login: String, password: String) {
        viewModelScope.launch {
            try {
                tokenRepository.loginAndGetToken(login, password)
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
