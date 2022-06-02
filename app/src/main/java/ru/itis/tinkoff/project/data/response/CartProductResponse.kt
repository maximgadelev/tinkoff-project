package ru.itis.tinkoff.project.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartProductResponse(
    @Json(name = "product")
    val product: ProductResponse,
    @Json(name = "quantity")
    val quantity: Int
)
