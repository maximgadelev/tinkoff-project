package ru.itis.tinkoff.project.data

import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.entity.Profile
import ru.itis.tinkoff.project.entity.Promotion
import java.math.BigDecimal

private const val PRICE = 5999
private const val NAME_PROMOTION = "8 марта"
private const val NAME_PRODUCT = "Фен MARTA MT-143"
private const val IMAGE_URL_PROMOTION =
    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg"
private const val IMAGE_URL_PRODUCT =
    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg"
private const val PROFILE_NAME = "Ivan"
private const val PROFILE_SURNAME = "Ivanov"
private const val PROFILE_IMAGE =
    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg"
private const val PRODUCT_COMPANY = "Burger King"

class StubApi : Api {

    override suspend fun getCategories(): List<Category> {
        return emptyList()
    }

    override suspend fun getProducts(): List<Product> {
        return getTestProducts()
    }

    override suspend fun getProfileInfo(): Profile {
        return getTestProfile()
    }

    override suspend fun getPromotions(): List<Promotion> {
        return getTestPromotions()
    }

    private fun getTestPromotions() = arrayListOf(
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        ),
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        ),
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        ),
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        ),
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        ),
        Promotion(
            NAME_PROMOTION,
            IMAGE_URL_PROMOTION
        )
    )

    private fun getTestProducts() = arrayListOf(
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY
        ),
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY

        ),
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY
        ),
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY
        ),
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY
        ),
        Product(
            NAME_PRODUCT,
            IMAGE_URL_PRODUCT,
            BigDecimal(PRICE),
            PRODUCT_COMPANY
        )
    )

    private fun getTestProfile() =
        Profile(
            PROFILE_NAME,
            PROFILE_SURNAME,
            PROFILE_IMAGE
        )
}
