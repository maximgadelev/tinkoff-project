package ru.itis.tinkoff.project.features.reviewsPage.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.reviewsPage.ui.renderer.ReviewCardListRenderer

sealed class ReviewsItem : ComparableItem {
    data class ReviewsCardListItem(
        override val reviews: List<ReviewCardListRenderer.Review>
    ) : ReviewsItem(),
        ReviewCardListRenderer.RenderContract
}
