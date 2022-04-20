package ru.itis.tinkoff.project.data.mapper

import android.net.Uri
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.data.response.ProductResponse
import ru.itis.tinkoff.project.data.response.PromotionResponse
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Promotion

class ResponseMapper {
    fun mapProductResponseToProduct(productResponses: List<ProductResponse>): List<Product> {
        return productResponses.map { productResponse ->
            Product(
                productResponse.name,
                checkImageList(productResponse.img),
                productResponse.price.toBigDecimal(),
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

    private fun checkImageList(list: List<String>): String {
        return if (list.isEmpty()) {
            return Uri.parse("android.resource://ru.itis.tinkoff.project/" + R.drawable.no_product)
                .toString()
        } else {
            list[0]
        }
    }
}
