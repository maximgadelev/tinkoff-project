package ru.itis.tinkoff.project.data.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val secondName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "password")
    val password: String
)
