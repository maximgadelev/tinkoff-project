package ru.itis.tinkoff.project.features.common.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_product_card_main.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic
import ru.itis.tinkoff.project.features.common.ProductCardItemType

class ProductCardRenderer<Item>(
    type: ProductCardItemType
) : ItemBaseRenderer<Item, ProductCardRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val id: Int
        val name: String
        val image: List<String>
        val characteristics: List<Characteristic>
        val price: String
        val description: String
        val company: String
    }

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.setOnClickListener { listener(viewHolder, it) }
    }

    override val layoutRes: Int = when (type) {
        ProductCardItemType.FAVORITE -> R.layout.item_product_card_favorites
        ProductCardItemType.MAIN -> R.layout.item_product_card_main
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            viewHolder.itemView.productImageView.load(item.image[0])
            itemView.productTitleTextView.text = item.name
            itemView.productPriceTextView.text = item.price
        }
    }
}
