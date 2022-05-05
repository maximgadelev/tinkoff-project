package ru.itis.tinkoff.project.features.productPage.ui

import android.util.Log
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageItemProvider {
    fun getItems(product: Product): List<ProductPageItem> {
        val products= product.image.map { productImage -> ProductPageItem.ProductImageListItem(productImage) }
        Log.e("aaa",products.get(1).image)
        return products
    }
}
