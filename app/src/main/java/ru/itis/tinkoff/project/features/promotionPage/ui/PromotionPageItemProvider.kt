package ru.itis.tinkoff.project.features.promotionPage.ui

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.promotionPage.utils.PromotionPageItem

class PromotionPageItemProvider(
    private val entityMapper: EntityMapper
) {
    fun getItems(products: List<Product>): List<PromotionPageItem> {
        val resultList = mutableListOf<PromotionPageItem>()
        resultList += PromotionPageItem.ProductListPromotionPageItem(
            entityMapper.mapProductToProductList(
                products
            )
        )
        return resultList
    }
}
