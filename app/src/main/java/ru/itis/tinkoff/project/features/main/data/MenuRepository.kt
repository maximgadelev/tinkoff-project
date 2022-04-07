package ru.itis.tinkoff.project.features.main.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion

class MenuRepository(
    private val api: Api
) {

    suspend fun getPromotions(): List<Promotion> {
        return api.getPromotions()
    }

    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}
