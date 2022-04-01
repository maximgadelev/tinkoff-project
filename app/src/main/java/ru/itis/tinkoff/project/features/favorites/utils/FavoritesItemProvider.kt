package ru.itis.tinkoff.project.features.favorites.utils

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper


class FavoritesItemProvider(
    private val entityMapper: EntityMapper
) {
    fun getItems(products: List<Product>): List<FavoritesItem> {
        val resultList = mutableListOf<FavoritesItem>()
        resultList += FavoritesItem.ProductListFavoritesItem(
            entityMapper.mapProductToProductList(
                products
            )
        )
        return resultList
    }
}
