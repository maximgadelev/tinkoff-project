package ru.itis.tinkoff.project.features.productPage.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Product

class ProductPageRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {
    suspend fun getProductById(id: Int): Product {
        return mapper.mapProductResponseToProduct(api.getProductById(id))
    }
}
