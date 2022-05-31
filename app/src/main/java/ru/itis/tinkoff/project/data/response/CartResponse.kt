package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "products")
    val products: List<CartProductResponse>,
    @Json(name = "totalSum")
    val totalSum: Int
)
