package ru.itis.tinkoff.project.features.registration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.registration.data.RegistrationRepository

class RegistrationFragmentViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()
    fun registerUser(
        firstName: String,
        secondName: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                registrationRepository.registerUser(
                    firstName,
                    secondName,
                    email,
                    phoneNumber,
                    password
                )
                eventChannel.send(Event.NavigateToConfirmEvent)
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
