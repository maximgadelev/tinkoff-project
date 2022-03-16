package ru.itis.tinkoff.project.features.utils


import com.bumptech.glide.Glide
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import kotlinx.android.synthetic.main.item_promotion.view.*
import ru.itis.tinkoff.project.features.utils.PromotionRender.RenderContract
class PromotionRender<Item> : ItemBaseRenderer<Item, RenderContract>() {

    interface RenderContract {
        val image: String
        val name:String
    }

    override val layoutRes: Int
        get() = R.layout.item_promotion

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        Glide.with(viewHolder.itemView)
            .load(item.image)
            .into(viewHolder.itemView.imageView)
    }
}