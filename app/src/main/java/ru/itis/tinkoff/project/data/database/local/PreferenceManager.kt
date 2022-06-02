package ru.itis.tinkoff.project.data.database.local

import android.content.SharedPreferences

class PreferenceManager(
    private val sharedPreference: SharedPreferences
) {
    fun storeToken(token: String) {
        sharedPreference.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreference.getString(TOKEN, DEFAULT_VALUE)
    }

    fun storeRefreshToken(refreshToken: String) {
        sharedPreference.edit().putString(REFRESH_TOKEN, refreshToken).apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreference.getString(REFRESH_TOKEN, DEFAULT_VALUE)
    }

    fun deleteToken() {
        sharedPreference.edit().putString(TOKEN, DEFAULT_VALUE).apply()
    }

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val TOKEN = "token"
        private val DEFAULT_VALUE = null
    }
}
