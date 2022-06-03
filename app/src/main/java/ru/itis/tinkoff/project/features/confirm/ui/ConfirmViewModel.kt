package ru.itis.tinkoff.project.features.confirm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.confirm.data.ConfirmRepository

class ConfirmViewModel(private val confirmRepository: ConfirmRepository) : ViewModel() {
    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()
    fun confirmUser(code: String) {
        viewModelScope.launch {
            try {
                confirmRepository.confirmUser(code)
                eventChannel.send(Event.NavigateToAuthorizationEvent)
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
