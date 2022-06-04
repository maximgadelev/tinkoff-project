package ru.itis.tinkoff.project.features.reviewsPage.ui.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_reviews_card_recycler.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ReviewCardListRenderer<Item> :
    ItemBaseRenderer<Item, ReviewCardListRenderer.RenderContract>() {
    interface RenderContract {
        val reviews: List<Review>
    }

    data class Review(
        override val id: Int,
        override val advantages: String,
        override val comment: String,
        override val date: String,
        override val disadvantages: String,
        override val experience: String,
        override val rating: Double,
        override val profileImage: String,
        override val authorName: String,
    ) : ReviewCardRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Review>().renderer(Review::class, ReviewCardRenderer()).build(
            DifferStrategies.withDiffUtilComparable()
        )
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView_reviews
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        return viewHolder
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        itemAdapter.differ.submitList(item.reviews)
    }

    override val layoutRes: Int
        get() = R.layout.item_reviews_card_recycler
}
