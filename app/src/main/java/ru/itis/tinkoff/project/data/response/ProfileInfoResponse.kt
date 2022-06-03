package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileInfoResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val secondName: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "profileImg")
    val profileImg: String
)
