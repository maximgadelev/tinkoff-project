package ru.itis.tinkoff.project.data.api

import retrofit2.http.GET
import ru.itis.tinkoff.project.data.response.ProductResponse
import ru.itis.tinkoff.project.data.response.PromotionResponse
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.entity.Profile

interface Api {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("profileInfo")
    suspend fun getProfileInfo(): Profile

    @GET("promotions")
    suspend fun getPromotions(): List<PromotionResponse>
}
