package ru.itis.tinkoff.project.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.menu.ui.renderer.*
import java.math.BigDecimal

sealed class Item:ComparableItem {

    data class CarouselItem(
        override val promotions: List<CarouselRenderer.Promotion>,
    ) : Item(), CarouselRenderer.RenderContract

    data class Title(
        override val titleRes: Int?,
    ) : Item(), TitleRenderer.RenderContract

    data class ProductItem(
        override val name: String,
        override val image: String,
        override val price: BigDecimal,
    ) : Item(), ProductCardRenderer.RenderContract

    data class SnapItem(
    override val promotions: List<SnapRenderer.Promotion>,
) : Item(), SnapRenderer.RenderContract

    data class ProductListItem(
        override val products: List<ProductCardListRenderer.Product>):Item(),
        ProductCardListRenderer.RenderContract

    data class ThreePromotionsCardItem(
        override val promotions: List<ThreePromotionsCardRenderer.Promotion>,
    ) : Item(), ThreePromotionsCardRenderer.RenderContract
}