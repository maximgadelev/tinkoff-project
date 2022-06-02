package ru.itis.tinkoff.project.features.common.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.count_button_product_page_view.view.*
import kotlinx.android.synthetic.main.item_product_card_favorites.view.*
import kotlinx.android.synthetic.main.item_product_card_main.view.productImageView
import kotlinx.android.synthetic.main.item_product_card_main.view.productPriceTextView
import kotlinx.android.synthetic.main.item_product_card_main.view.productTitleTextView
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic
import ru.itis.tinkoff.project.features.common.ProductCardItemType

class ProductCardRenderer<Item>(
    val type: ProductCardItemType
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
        if (type == ProductCardItemType.FAVORITE) {
            viewHolder.itemView.buttonToCardFavorite.setOnClickListener { buttonToCard ->
                listener(viewHolder, buttonToCard)
                viewHolder.itemView.countButtonFavorites.visibility = View.VISIBLE
                listener(viewHolder, viewHolder.itemView.textViewQuantity)
            }
            viewHolder.itemView.countButtonFavorites.imageButton_plusQuantity.setOnClickListener {
                listener(viewHolder, it)
                val button = viewHolder.itemView.countButtonFavorites
                button.setCount(viewHolder.itemView.countButtonFavorites.getCount() + 1)
                listener(viewHolder, viewHolder.itemView.textViewQuantity)
            }
            viewHolder.itemView.countButtonFavorites.imageButton_minusQuantity.setOnClickListener {
                listener(viewHolder, it)
                val button = viewHolder.itemView.countButtonFavorites
                button.setCount(viewHolder.itemView.countButtonFavorites.getCount() - 1)
                listener(viewHolder, viewHolder.itemView.textViewQuantity)
            }
        }
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
            if (type == ProductCardItemType.FAVORITE) {
                itemView.buttonToCardFavorite
            }
        }
    }
}
