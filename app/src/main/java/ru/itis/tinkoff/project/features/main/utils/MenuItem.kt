package ru.itis.tinkoff.project.features.main.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.TitleRenderer

sealed class MenuItem : ComparableItem {

    data class CarouselMenuItem(
        override val promotions: List<CarouselRenderer.Promotion>,
    ) : MenuItem(), CarouselRenderer.RenderContract

    data class Title(
        override val titleRes: Int?,
    ) : MenuItem(), TitleRenderer.RenderContract


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
