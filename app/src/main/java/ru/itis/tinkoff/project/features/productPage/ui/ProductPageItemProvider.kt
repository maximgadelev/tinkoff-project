package ru.itis.tinkoff.project.features.productPage.ui

import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageItemProvider {
    fun getItems(product: Product): List<ProductPageItem> {
        return product.image.map { productImage -> ProductPageItem.ProductImageListItem(productImage) }
    }
}
