package ru.itis.tinkoff.project.features.cart.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.entity.Product

class CartRepository(
    private val api: Api
) {

    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}
