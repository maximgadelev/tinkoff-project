package ru.itis.tinkoff.project.features.common.data

import ru.itis.tinkoff.project.data.LoginRequest
import ru.itis.tinkoff.project.data.api.TokenApi
import ru.itis.tinkoff.project.data.database.local.PreferenceManager
import ru.itis.tinkoff.project.data.response.TokenResponse

class TokenRepository(
    private val api: TokenApi,
    private var preferenceManager: PreferenceManager
) {
    suspend fun getToken(login: String, password: String): TokenResponse {
        val response = api.getToken(LoginRequest(login, password))
        response.accessToken?.let { preferenceManager.storeToken(it) }
        response.refreshToken?.let { preferenceManager.storeRefreshToken(it) }
        return response
    }

    suspend fun refreshToken(): TokenResponse {
        val response = api.refreshAccessToken(preferenceManager.getRefreshToken())
        val token = response.accessToken
        val refreshToken = response.refreshToken
        token?.let { preferenceManager.storeToken(it) }
        refreshToken?.let { preferenceManager.storeRefreshToken(it) }
        return response
    }

    fun getToken(): String? {
        return preferenceManager.getToken()
    }

    fun saveToken(token: String) {
        preferenceManager.storeToken(token)
    }

    fun saveRefreshToken(refreshToken: String) {
        preferenceManager.storeToken(refreshToken)
    }

    fun getRefreshToken() {
        preferenceManager.getRefreshToken()
    }
}
