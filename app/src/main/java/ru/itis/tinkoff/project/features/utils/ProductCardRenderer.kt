package ru.itis.tinkoff.project.features.utils

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_card.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R


class ProductCardRenderer<Item> : ItemBaseRenderer<Item, ProductCardRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val name: String
        val image: String
        val price: Int
    }

    override val layoutRes: Int
        get() = R.layout.product_card

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            Glide.with(viewHolder.itemView)
                .load(item.image)
                .into(viewHolder.itemView.productImageView)
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price.toString() + " Р"
        }
    }

}