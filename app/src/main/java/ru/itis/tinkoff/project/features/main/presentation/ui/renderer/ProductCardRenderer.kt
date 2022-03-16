package ru.itis.tinkoff.project.features.main.presentation.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_product_card.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ProductCardRenderer<Item> : ItemBaseRenderer<Item, ProductCardRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val name: String
        val image: String
        val price: String
    }

    override val layoutRes: Int
        get() = R.layout.item_product_card

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            viewHolder.itemView.productImageView.load(item.image)
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price
        }
    }

}
