package ru.itis.tinkoff.project.features.main.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion

class MenuRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {

    suspend fun getPromotions(): List<Promotion> {
        return mapper.mapPromotionsResponseToPromotion(api.getPromotions())
    }

    suspend fun getProducts(): List<Product> {
        return mapper.mapProductResponseToProduct(api.getProducts())
    }
}
