package ru.itis.tinkoff.project.features.cart.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Product

class CartRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {

    suspend fun getProducts(): List<Product> {
        return mapper.mapProductResponseToProduct(api.getProducts())
    }
}
