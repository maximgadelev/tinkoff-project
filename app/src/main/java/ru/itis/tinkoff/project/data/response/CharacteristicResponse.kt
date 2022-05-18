package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CharacteristicResponse(
    @Json(name = "characteristic")
    val characteristic: String,
    @Json(name = "type")
    val type: String
)
