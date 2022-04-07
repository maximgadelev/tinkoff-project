package ru.itis.tinkoff.project.features.main.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_promotion_long.view.*
import kotlinx.android.synthetic.main.item_promotion_square_100dp.view.*
import kotlinx.android.synthetic.main.item_promotion_square_200dp.view.*
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.main.ui.renderer.PromotionRender.RenderContract
import ru.itis.tinkoff.project.features.main.utils.PromotionItemSize

class PromotionRender<Item>(size: PromotionItemSize) : ItemBaseRenderer<Item, RenderContract>() {
    interface RenderContract {
        val image: String
        val name: String
    }

    override val layoutRes: Int = when (size) {
        PromotionItemSize.LONG -> R.layout.item_promotion_long
        PromotionItemSize.SQUARE_200 -> R.layout.item_promotion_square_200dp
        PromotionItemSize.SQUARE_100 -> R.layout.item_promotion_square_100dp
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        when (layoutRes) {
            R.layout.item_promotion_long ->
                viewHolder.itemView.imageView_long.load(item.image)

            R.layout.item_promotion_square_200dp ->
                viewHolder.itemView.imageView_square_200.load(item.image)

            R.layout.item_promotion_square_100dp ->
                viewHolder.itemView.imageView_square_100.load(item.image)
        }
    }
}
