package ru.itis.tinkoff.project.features.main.ui

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.main.utils.MenuItem

class MenuItemProvider(
    private val entityMapper: EntityMapper
) {
    fun getItemList(products: List<Product>, promotions: List<Promotion>): List<MenuItem> {
        val resultList = mutableListOf<MenuItem>()
        resultList += MenuItem.SnapMenuItem(entityMapper.mapPromotionToSnapRenderItem(promotions))
        resultList += MenuItem.Title(R.string.title)
        resultList += MenuItem.ProductListMenuItem(entityMapper.mapProductToProductList(products))
        resultList += MenuItem.CarouselMenuItem(
            entityMapper.mapPromotionToCarouselRenderItem(
                promotions
            )
        )
        resultList += MenuItem.ThreePromotionsCardMenuItem(
            entityMapper.mapPromotionToSnapRenderItem(promotions).slice(0..2)
        )
        return resultList
    }
}
