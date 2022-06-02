package ru.itis.tinkoff.project.features.common.mapper

import ru.itis.tinkoff.project.entity.CartProduct
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion
import ru.itis.tinkoff.project.features.cart.ui.renderer.CartProductListRenderer
import ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer.CatalogRenderer
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
                product.companyName,
            )
        }
    }

    fun mapProductToCartProductList(products: List<CartProduct>): List<CartProductListRenderer.Product> {
        return products.map { cartProduct ->
            CartProductListRenderer.Product(
                cartProduct.product.id,
                cartProduct.product.name,
                cartProduct.product.image,
                cartProduct.product.characteristic,
                cartProduct.product.price.toString() + " ₽",
                cartProduct.product.description,
                cartProduct.product.companyName,
                "x " + cartProduct.quantity.toString()
            )
        }
    }

    fun mapCategoryToCatalogListItem(categories : List<Category>): List<CatalogRenderer.Category> {
        return categories.map {  category ->
            CatalogRenderer.Category(
                category.name,
                category.photo
            )
        }
    }
}
