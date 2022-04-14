package ru.itis.tinkoff.project.data

import ru.itis.tinkoff.project.entity.*
import java.math.BigDecimal

private const val PRICE = 5999
private const val NAME_PROMOTION = "8 марта"
private const val NAME_PRODUCT = "Фен MARTA MT-143"
private const val IMAGE_URL_PROMOTION =
    "https://kartinkin.net/uploads/posts/2021-07/1625779006_25-kartinkin-com-p-krutie-oranzhevie-oboi-krasivie-27.jpg"
private const val IMAGE_URL_PRODUCT =
    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg"

private const val USER_ID: Int = 0
private const val USER_NAME = "Ivan"
private const val USER_SURNAME = "Ivanov"
private const val USER_EMAIL = "some_email@.com"
private const val USER_PHONE_NUMBER = "79467384573"
private const val USER_PASSWORD = "pass"

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

    override suspend fun getProfileInfo(id: Int): User {
    return User(USER_ID, USER_NAME, USER_SURNAME, USER_EMAIL, USER_PHONE_NUMBER, USER_PASSWORD, null)
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
}
