package ru.itis.tinkoff.project.features.common.renderer

import coil.load
import kotlinx.android.synthetic.main.item_product_card_main.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.common.ProductCardItemType

class ProductCardRenderer<Item>(
    type: ProductCardItemType
) : ItemBaseRenderer<Item, ProductCardRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val name: String
        val image: String
        val price: String
    }

    override val layoutRes: Int = when (type) {
        ProductCardItemType.FAVORITE -> R.layout.item_product_card_favorites
        ProductCardItemType.MAIN -> R.layout.item_product_card_main
    }


    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            viewHolder.itemView.productImageView.load(item.image)
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price
        }
    }
}

