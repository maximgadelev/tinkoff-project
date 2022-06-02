package ru.itis.tinkoff.project.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductToCartRequest(
    val id: Int,
    val quantity: Int
)
