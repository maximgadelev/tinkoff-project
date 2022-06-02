package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "category")
    val categoryResponse: CategoryResponse,
    @Json(name = "characteristic")
    val characteristicResponse: List<CharacteristicResponse>,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "img")
    val img: List<String>,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "brand")
    val brand: String,
)
