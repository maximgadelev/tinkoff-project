package ru.itis.tinkoff.project.data.mapper

import android.net.Uri
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.data.response.ProductResponse
import ru.itis.tinkoff.project.data.response.PromotionResponse
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion

class ResponseMapper {
    fun mapProductsResponseToProducts(productResponses: List<ProductResponse>): List<Product> {
        return productResponses.map { productResponse ->
            Product(
                productResponse.id,
                productResponse.name,
                checkImageList(productResponse.img),
                productResponse.price.toBigDecimal(),
                productResponse.description,
                "New Company" // заглушка,на беке еще нет этого поля
            )
        }
    }

    fun mapPromotionsResponseToPromotion(promotionResponses: List<PromotionResponse>): List<Promotion> {
        return promotionResponses.map { promotionResponse ->
            Promotion(
                promotionResponse.name,
                promotionResponse.img
            )
        }
    }

    fun mapProductResponseToProduct(productResponse: ProductResponse): Product {
        return Product(
            productResponse.id,
            productResponse.name,
            checkImageList(productResponse.img),
            productResponse.price.toBigDecimal(),
            productResponse.description,
            "New Company"
        )
    }

    private fun checkImageList(list: List<String>): List<String> {
        return if (list.isEmpty()) {
            val newList = mutableListOf<String>()
            newList.add(
                Uri.parse("android.resource://ru.itis.tinkoff.project/" + R.drawable.no_product)
                    .toString()
            )
            newList
        } else {
            list
        }
    }
}
