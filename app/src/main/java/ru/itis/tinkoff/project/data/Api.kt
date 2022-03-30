package ru.itis.tinkoff.project.data

import retrofit2.http.GET
import ru.itis.tinkoff.project.entity.*

interface Api {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("profileInfo")
    suspend fun getProfileInfo(id: Long): User

    @GET("promotions")
    suspend fun getPromotions(): List<Promotion>
}
