package ru.itis.tinkoff.project.features.productPage.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.productPage.ui.renderer.ProductImageListRenderer

sealed class ProductPageItem : ComparableItem {
    data class ProductImageListItem(
        override val image: String
    ) : ProductPageItem(), ProductImageListRenderer.RenderContract
}
