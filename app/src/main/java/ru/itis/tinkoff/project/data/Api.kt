package ru.itis.tinkoff.project.data

import retrofit2.http.GET
import ru.itis.tinkoff.project.entity.User

interface Api {
    @GET()
    suspend fun getCategories()
    suspend fun getProducts()
    suspend fun getProfileInfo() : User
    suspend fun getPromotions()
}
