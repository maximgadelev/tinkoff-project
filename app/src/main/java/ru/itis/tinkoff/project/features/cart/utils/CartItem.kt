package ru.itis.tinkoff.project.features.cart.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer

sealed class CartItem : ComparableItem {
    data class ProductListCartItem(
        override val products: List<ProductCardListRenderer.Product>
    ) : CartItem(),
        ProductCardListRenderer.RenderContract
}
