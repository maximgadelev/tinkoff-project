package ru.itis.tinkoff.project.data.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfirmRequest(
    @Json(name = "confirm_code")
    val confirmCode: String
)
