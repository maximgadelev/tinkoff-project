package ru.itis.tinkoff.project.features.promotionPage.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer

sealed class PromotionPageItem : ComparableItem {
    data class ProductListPromotionPageItem(
        override val products: List<ProductCardListRenderer.Product>
    ) : PromotionPageItem(),
        ProductCardListRenderer.RenderContract
}
