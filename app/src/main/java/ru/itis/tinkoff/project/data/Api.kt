package ru.itis.tinkoff.project.data

import retrofit2.http.GET
import ru.itis.tinkoff.project.entity.User

interface Api {
    @GET()
    suspend fun getCategories()

    @GET()
    suspend fun getProducts()

    @GET()
    suspend fun getProfileInfo(id: Long) : User

    @GET()
    suspend fun getPromotions()
}
