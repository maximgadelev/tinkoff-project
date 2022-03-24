package ru.itis.tinkoff.project.features.main.presentation.mapper

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.main.presentation.ui.renderer.SnapRenderer

class EntityMapper {

    fun mapPromotionToSnapRenderItem(promotions: List<Promotion>): List<SnapRenderer.Promotion> {
        return promotions.map { promotion ->
            SnapRenderer.Promotion(
                promotion.image,
                promotion.name
            )
        }
    }

    fun mapPromotionToCarouselRenderItem(promotions: List<Promotion>): List<CarouselRenderer.Promotion> {
        return promotions.map { promotion ->
            CarouselRenderer.Promotion(
                promotion.image,
                promotion.name
            )
        }
    }


    fun mapProductToProductList(products: List<Product>): List<ProductCardListRenderer.Product> {
        return products.map { product ->
            ProductCardListRenderer.Product(
                product.name,
                product.image,
                product.price.toString() + " Р"
            )
        }
    }

}