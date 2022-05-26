package ru.itis.tinkoff.project.features.promotionPage.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Product

class PromotionRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {
    suspend fun getProducts(id: Int): List<Product> {
        return mapper.mapProductsResponseToProducts(api.getProductByPromotionId(id))
    }
}
