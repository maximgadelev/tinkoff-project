package ru.itis.tinkoff.project.features.cart.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.CartInfo

class CartRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {

    suspend fun getProducts(): CartInfo {
        return mapper.mapCartResponseToCartInfo(api.getCartProducts())
    }

    suspend fun getTotalSum(): Int {
        return api.getCartProducts().totalSum
    }
}
