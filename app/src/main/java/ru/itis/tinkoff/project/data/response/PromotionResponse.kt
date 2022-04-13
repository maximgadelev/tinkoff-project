package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PromotionResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "img")
    val img: String,
    @Json(name = "isActive")
    val isActive: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "products")
    val products: List<ProductResponse>
)
