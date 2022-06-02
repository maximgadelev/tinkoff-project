package ru.itis.tinkoff.project.data.repository

import ru.itis.tinkoff.project.data.api.TokenApi
import ru.itis.tinkoff.project.data.database.local.PreferenceManager
import ru.itis.tinkoff.project.data.request.LoginRequest
import ru.itis.tinkoff.project.data.response.TokenResponse

class TokenRepository(
    private val tokenApi: TokenApi,
    private var preferenceManager: PreferenceManager
) {
    suspend fun loginAndGetToken(login: String, password: String): TokenResponse {
        val response = tokenApi.getToken(LoginRequest(login, password))
        response.accessToken?.let { preferenceManager.storeToken(it) }
        response.refreshToken?.let { preferenceManager.storeRefreshToken(it) }
        return response
    }

    suspend fun refreshToken(): TokenResponse {
        val response = tokenApi.refreshAccessToken(preferenceManager.getRefreshToken())
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
