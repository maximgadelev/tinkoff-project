package ru.itis.tinkoff.project.features.favorites.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Product

class FavoritesRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {

    suspend fun getProducts(): List<Product> {
        return mapper.mapProductResponseToProduct(api.getProducts())
    }
}
