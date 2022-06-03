package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "profileImg")
    val profileImg: String?,
    @Json(name = "role")
    val role: String
)
