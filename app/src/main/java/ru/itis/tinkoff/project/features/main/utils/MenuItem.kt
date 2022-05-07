package ru.itis.tinkoff.project.features.main.utils

import android.view.Menu
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.SnapRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.TitleRenderer

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

    data class ProductCardItem(
        override val id: Int,
        override val name: String,
        override val image: List<String>,
        override val price: String,
        override val description: String,
        override val company: String

    ) : MenuItem(), ProductCardRenderer.RenderContract
}
