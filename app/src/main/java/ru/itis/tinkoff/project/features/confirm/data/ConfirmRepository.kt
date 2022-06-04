package ru.itis.tinkoff.project.features.confirm.data

import ru.itis.tinkoff.project.data.api.TokenApi

class ConfirmRepository(
    private val tokenApi: TokenApi
) {
    suspend fun confirmUser(code: String) {
        return tokenApi.confirm(code)
    }
}
