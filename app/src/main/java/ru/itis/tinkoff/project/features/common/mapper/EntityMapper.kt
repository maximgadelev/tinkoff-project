package ru.itis.tinkoff.project.features.common.mapper

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.cart.ui.renderer.CartProductListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.CarouselRenderer
import ru.itis.tinkoff.project.features.main.ui.renderer.SnapRenderer

const val PRODUCT_NAME_MAX_SIZE = 25
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
                replaceProductName(product.name),
                product.image,
                product.price.toString() + " ₽",
                product.companyName
            )
        }
    }

    fun mapProductToCartProductList(products: List<Product>): List<CartProductListRenderer.Product> {
        return products.map { product ->
            CartProductListRenderer.Product(
                product.name,
                product.image,
                product.price.toString() + " ₽",
                product.companyName
            )
        }
    }
private fun replaceProductName(string: String): String {
    if (string.length> PRODUCT_NAME_MAX_SIZE) {
        return string.replaceRange(string.length / 2 until string.length, "..")
    }
    return string
}
}
