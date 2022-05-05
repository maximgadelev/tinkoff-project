package ru.itis.tinkoff.project.features.cart.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_product_card_cart.view.*
import kotlinx.android.synthetic.main.item_product_card_cart.view.productImageView
import kotlinx.android.synthetic.main.item_product_card_cart.view.productPriceTextView
import kotlinx.android.synthetic.main.item_product_card_cart.view.productTitleTextView
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class CartProductRenderer<Item> : ItemBaseRenderer<Item, CartProductRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val name: String
        val image: List<String>
        val price: String
        val company: String
    }

    override val layoutRes: Int
        get() = R.layout.item_product_card_cart

    override fun onBindView(viewHolder: BaseViewHolder, item: CartProductRenderer.RenderContract) {
        with(viewHolder) {
            viewHolder.itemView.productImageView.load(item.image[0])
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price
            itemView.cartProductCompanyTextView.text = item.company
        }
    }
}
