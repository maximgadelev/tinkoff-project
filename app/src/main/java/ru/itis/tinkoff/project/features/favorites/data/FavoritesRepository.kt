package ru.itis.tinkoff.project.features.favorites.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.entity.Product

class FavoritesRepository(
    private val api: Api
) {

    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}
