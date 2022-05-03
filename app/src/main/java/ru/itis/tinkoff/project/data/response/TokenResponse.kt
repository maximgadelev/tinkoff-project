package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @Json(name = "accessToken")
    val accessToken: String?,
    @Json(name = "refreshToken")
    val refreshToken: String?,
)
