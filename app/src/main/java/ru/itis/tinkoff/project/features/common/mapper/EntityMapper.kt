package ru.itis.tinkoff.project.features.common.mapper

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.cart.ui.renderer.CartProductListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.SnapRenderer

class EntityMapper {

    fun mapPromotionToSnapRenderItem(promotions: List<Promotion>): List<SnapRenderer.Promotion> {
        return promotions.map { promotion ->
            SnapRenderer.Promotion(
                promotion.id,
                promotion.name,
                promotion.isActive,
                promotion.image,
                promotion.products
            )
        }
    }

    fun mapPromotionToCarouselRenderItem(promotions: List<Promotion>): List<CarouselRenderer.Promotion> {
        return promotions.map { promotion ->
            CarouselRenderer.Promotion(
                promotion.id,
                promotion.name,
                promotion.isActive,
                promotion.image,
                promotion.products
            )
        }
    }

    fun mapProductToProductList(products: List<Product>): List<ProductCardListRenderer.Product> {
        return products.map { product ->
            ProductCardListRenderer.Product(
                product.id,
                product.name,
                product.image,
                product.characteristic,
                product.price.toString() + " ₽",
                product.description,
                product.companyName
            )
        }
    }

    fun mapProductToCartProductList(products: List<Product>): List<CartProductListRenderer.Product> {
        return products.map { product ->
            CartProductListRenderer.Product(
                product.id,
                product.name,
                product.image,
                product.characteristic,
                product.price.toString() + " ₽",
                product.description,
                product.companyName
            )
        }
    }
}
