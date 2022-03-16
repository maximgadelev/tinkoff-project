package ru.itis.tinkoff.project.data

import retrofit2.http.GET
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Profile
import ru.itis.tinkoff.project.entity.Promotion

interface Api {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("profileInfo")
    suspend fun getProfileInfo(): Profile

    @GET("promotions")
    suspend fun getPromotions(): List<Promotion>
}
