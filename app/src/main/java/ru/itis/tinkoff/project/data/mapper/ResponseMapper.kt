package ru.itis.tinkoff.project.data.mapper

import android.net.Uri
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.data.response.*
import ru.itis.tinkoff.project.entity.*

class ResponseMapper {
    fun mapProductsResponseToProducts(productResponses: List<ProductResponse>): List<Product> {
        return productResponses.map { productResponse ->
            Product(
                productResponse.id,
                productResponse.name,
                checkImageList(productResponse.img),
                mapCharacteristic(productResponse.characteristicResponse),
                productResponse.price.toBigDecimal(),
                productResponse.description,
                productResponse.brand,
                productResponse.rating
            )
        }
    }

    fun mapPromotionsResponseToPromotion(promotionResponses: List<PromotionResponse>): List<Promotion> {
        return promotionResponses.map { promotionResponse ->
            Promotion(
                promotionResponse.id,
                promotionResponse.name,
                promotionResponse.isActive,
                promotionResponse.img,
                mapProductsResponseToProducts(promotionResponse.products)
            )
        }
    }

    fun mapProductResponseToProduct(productResponse: ProductResponse): Product {
        return Product(
            productResponse.id,
            productResponse.name,
            checkImageList(productResponse.img),
            mapCharacteristic(productResponse.characteristicResponse),
            productResponse.price.toBigDecimal(),
            productResponse.description,
            productResponse.brand,
            productResponse.rating
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

    private fun mapCharacteristic(characteristics: List<CharacteristicResponse>): List<Characteristic> {
        return characteristics.map { characteristicResponse ->
            Characteristic(
                characteristicResponse.characteristic,
                characteristicResponse.type
            )
        }
    }

    fun mapReviewList(reviews: List<ReviewResponse>): List<Review> {
        return reviews.map { reviewResponse ->
            Review(
                reviewResponse.id,
                reviewResponse.advantages,
                reviewResponse.comment,
                reviewResponse.date,
                reviewResponse.disadvantages,
                reviewResponse.experience,
                reviewResponse.rating,
                reviewResponse.profile.profileImg.toString(),
                reviewResponse.profile.firstName + " " + reviewResponse.profile.lastName
            )
        }
    }

    fun mapCartResponseToCartInfo(cartResponse: CartResponse): CartInfo {
        return CartInfo(
            mapCartProductToCartProductList(cartResponse.products),
            cartResponse.totalSum
        )
    }

    private fun mapCartProductToCartProductList(list: List<CartProductResponse>): List<CartProduct> {
        return list.map { responseProduct ->
            CartProduct(
                mapProductResponseToProduct(responseProduct.product),
                responseProduct.quantity
            )
        }
    }

    fun mapProfileResponseToProfile(profileResponse: ProfileResponse): Profile {
        return Profile(
            profileResponse.firstName,
            profileResponse.lastName,
            profileResponse.profileImg
        )
    }
}
