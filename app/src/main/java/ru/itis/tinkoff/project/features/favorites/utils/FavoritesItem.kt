package ru.itis.tinkoff.project.features.favorites.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer


sealed class FavoritesItem:ComparableItem {
    data class ProductListFavoritesItem(
        override val products: List<ProductCardListRenderer.Product>
    ) : FavoritesItem(),
        ProductCardListRenderer.RenderContract
}
