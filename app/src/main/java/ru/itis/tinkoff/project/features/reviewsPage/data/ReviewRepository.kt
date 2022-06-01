package ru.itis.tinkoff.project.features.reviewsPage.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.entity.Review

class ReviewRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {
    suspend fun getReviews(id: Int): List<Review> {
        return mapper.mapReviewList(api.getReviewsByProductsId(id))
    }
}
