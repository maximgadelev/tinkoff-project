package ru.itis.tinkoff.project.features.cart.ui

import ru.itis.tinkoff.project.entity.CartProduct
import ru.itis.tinkoff.project.features.cart.utils.CartItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CartItemProvider(
    private val entityMapper: EntityMapper
) {
    fun getItems(products: List<CartProduct>): List<CartItem> {
        val resultList = mutableListOf<CartItem>()
        resultList += CartItem.ProductListCartItem(
            entityMapper.mapProductToCartProductList(
                products
            )
        )
        return resultList
    }
}
