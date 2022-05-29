package ru.itis.tinkoff.project.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.response.CategoryResponse
import ru.itis.tinkoff.project.data.response.ProductResponse
import ru.itis.tinkoff.project.data.response.PromotionResponse
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.entity.Profile

private const val PROFILE_NAME = "Ivan"
private const val PROFILE_SURNAME = "Ivanov"
private const val PROFILE_IMAGE =
    "https://markshmidt.ru/wa-data/public/shop/products/04/05/504/images/1108/1108.970.jpg"

@SuppressWarnings("MaxLineLength, MagicNumber")
class StubApi : Api {

    override suspend fun getCategories(): List<Category> {
        return emptyList()
    }

    override suspend fun getProducts(): List<ProductResponse> {
        return emptyList()
    }

    override suspend fun getProfileInfo(): Profile {
        return getTestProfile()
    }

    override suspend fun getPromotions(): List<PromotionResponse> {
        return emptyList()
    }

    override suspend fun getProductById(id: Int): ProductResponse {
        return ProductResponse(
            CategoryResponse(1, "123"),
            emptyList(),
            "123",
            1,
            emptyList(),
            "32131",
            price = 123,
            1.0
        ,"PFPFPF"
        ) // просто,чтоб пока конфликтов у девочек не было,потом уберем stubApi
    }

    override suspend fun getProductByPromotionId(id: Int): List<ProductResponse> {
        return emptyList()
    }

    private fun getTestProfile() =
        Profile(
            PROFILE_NAME,
            PROFILE_SURNAME,
            PROFILE_IMAGE
        )
}
