package ru.itis.tinkoff.project.features.reviewsPage.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_review.view.*
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ReviewCardRenderer<Item> : ItemBaseRenderer<Item, ReviewCardRenderer.RenderContract>() {
    interface RenderContract {
        val id: Int
        val advantages: String
        val comment: String
        val date: String
        val disadvantages: String
        val experience: String
        val rating: Double
        val profileImage: String
        val authorName: String
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.ratingBar_review.rating = item.rating.toFloat()
            itemView.textView_disadvantages_description.text = item.disadvantages
            itemView.textView_advantages_description.text = item.advantages
            itemView.textView_experience_description.text = item.experience
            itemView.textView_comment_description.text = item.comment
            itemView.textView_reviewDate.text = item.date
            itemView.textView_profileName.text = item.authorName
            itemView.circleImageView_profile.load(item.profileImage)
        }
    }

    override val layoutRes: Int
        get() = R.layout.item_review
}
