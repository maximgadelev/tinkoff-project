package ru.itis.tinkoff.project.features.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.menu.ui.renderer.TitleRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.ProductCardRenderer
import ru.itis.tinkoff.project.features.menu.ui.renderer.PromotionRender

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
        override val price: Int,
    ) : Item(), ProductCardRenderer.RenderContract

    data class ProductListItem(
        override val products: List<ProductCardListRenderer.Product>):Item(),
        ProductCardListRenderer.RenderContract
}