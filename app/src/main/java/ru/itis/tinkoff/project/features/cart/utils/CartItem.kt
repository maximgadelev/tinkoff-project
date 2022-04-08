package ru.itis.tinkoff.project.features.cart.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.cart.ui.renderer.CartProductListRenderer

sealed class CartItem : ComparableItem {
    data class ProductListCartItem(
        override val products: List<CartProductListRenderer.Product>
    ) : CartItem(),
        CartProductListRenderer.RenderContract
}
