package ru.itis.tinkoff.project.features.cart.ui.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_product_card_cart.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic

class CartProductRenderer<Item> : ItemBaseRenderer<Item, CartProductRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val id: Int
        val name: String
        val image: List<String>
        val characteristics: List<Characteristic>
        val price: String
        val description: String
        val company: String
        val quantity: String
    }

    override val layoutRes: Int
        get() = R.layout.item_product_card_cart

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.imageButtonDeleteCart.setOnClickListener { listener(viewHolder, it) }
        viewHolder.itemView.setOnClickListener { listener(viewHolder, it) }
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: CartProductRenderer.RenderContract) {
        with(viewHolder) {
            viewHolder.itemView.productImageView.load(item.image[0])
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price
            itemView.textViewQuantity.text = item.quantity
            itemView.cartProductCompanyTextView.text = item.company
        }
    }
}
