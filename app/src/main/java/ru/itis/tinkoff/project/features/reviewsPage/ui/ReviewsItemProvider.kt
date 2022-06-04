package ru.itis.tinkoff.project.features.reviewsPage.ui

import ru.itis.tinkoff.project.entity.Review
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.reviewsPage.utils.ReviewsItem

class ReviewsItemProvider(
    private val entityMapper: EntityMapper
) {
    fun getItems(reviews: List<Review>): List<ReviewsItem> {
        val resultList = mutableListOf<ReviewsItem>()
        resultList += ReviewsItem.ReviewsCardListItem(
            entityMapper.mapReviewsToReviewsItem(
                reviews
            )
        )
        return resultList
    }
}
