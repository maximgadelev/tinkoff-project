package ru.itis.tinkoff.project.data.api

import retrofit2.http.*
import ru.itis.tinkoff.project.data.request.AddProductToCartRequest
import ru.itis.tinkoff.project.data.response.*

interface Api {
    @GET("categories")
    suspend fun getCategories(): List<CategoryResponse>

    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("profile")
    suspend fun getProfileInfo(): ProfileResponse

    @GET("promotions")
    suspend fun getPromotions(): List<PromotionResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductResponse

    @GET("products/promotion/{promotion_id}")
    suspend fun getProductByPromotionId(@Path("promotion_id") id: Int): List<ProductResponse>

    @GET("review/{productId}")
    suspend fun getReviewsByProductsId(@Path("productId") id: Int): List<ReviewResponse>

    @GET("bucket")
    suspend fun getCartProducts(): CartResponse

    @Headers("Content-Type: application/json")
    @POST("bucket/add")
    suspend fun addProductToCart(
        @Body addProductToCartRequest: AddProductToCartRequest
    )

    @DELETE("bucket/remove/{product_id}")
    suspend fun deleteProductFromCart(@Path("product_id") id: Int)
}
