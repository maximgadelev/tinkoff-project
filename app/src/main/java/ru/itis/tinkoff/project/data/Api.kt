package ru.itis.tinkoff.project.data

import retrofit2.http.GET

interface Api {
    @GET()
    suspend fun getCategories()
    suspend fun getProducts()
    suspend fun getProfileInfo()
    suspend fun getPromotions()
}