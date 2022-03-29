package ru.itis.tinkoff.project.features.main.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.*

sealed class MenuItem : ComparableItem {

    data class CarouselMenuItem(
        override val promotions: List<CarouselRenderer.Promotion>,
    ) : MenuItem(), CarouselRenderer.RenderContract

    data class Title(
        override val titleRes: Int?,
    ) : MenuItem(), TitleRenderer.RenderContract

    data class ProductMenuItem(
        override val name: String,
        override val image: String,
        override val price: String,
    ) : MenuItem(), ProductCardRenderer.RenderContract

    data class SnapMenuItem(
        override val promotions: List<SnapRenderer.Promotion>,
    ) : MenuItem(), SnapRenderer.RenderContract

    data class ProductListMenuItem(
        override val products: List<ProductCardListRenderer.Product>
    ) : MenuItem(),
        ProductCardListRenderer.RenderContract

    data class ThreePromotionsCardMenuItem(
        override val promotions: List<SnapRenderer.Promotion>,
    ) : MenuItem(), SnapRenderer.RenderContract
}
