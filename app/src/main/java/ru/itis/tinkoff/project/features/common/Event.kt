package ru.itis.tinkoff.project.features.common

sealed class Event {
    object NavigateToMenuEvent : Event()
    object ExceptionEvent : Event()
}
