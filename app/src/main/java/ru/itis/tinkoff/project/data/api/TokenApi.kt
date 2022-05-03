package ru.itis.tinkoff.project.data.api

import androidx.annotation.RawRes
import retrofit2.http.*
import ru.itis.tinkoff.project.data.LoginRequest
import ru.itis.tinkoff.project.data.response.TokenResponse

interface TokenApi {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun getToken(
        @Body loginRequest: LoginRequest
    ): TokenResponse

    @FormUrlEncoded
    @POST("token/refresh")
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String?
    ): TokenResponse
}
