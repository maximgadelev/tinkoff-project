package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "advantages")
    val advantages: String,
    @Json(name = "comment")
    val comment: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "disadvantages")
    val disadvantages: String,
    @Json(name = "experience")
    val experience: String,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "user")
    val profile: ProfileResponse
)
