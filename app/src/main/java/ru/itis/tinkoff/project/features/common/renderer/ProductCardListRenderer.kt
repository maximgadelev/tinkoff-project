package ru.itis.tinkoff.project.features.common.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.common.ProductCardItemType

class ProductCardListRenderer<Item>(type: ProductCardItemType) :
    ItemBaseRenderer<Item, ProductCardListRenderer.RenderContract>(), ClickableRenderer {

    interface RenderContract {
        @get:IdRes
        val id: Int?
            get() = null
        val products: List<Product>
    }

    data class Product(
        override val name: String,
        override val image: String,
        override val price: String,
        override val company:String
    ) : ProductCardRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Product>()
            .renderer(Product::class, ProductCardRenderer(type))
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.setOnClickListener {
            listener(viewHolder, it)
        }
    }

    override val layoutRes: Int = when (type) {
        ProductCardItemType.MAIN -> R.layout.item_product_card_recycler
        ProductCardItemType.FAVORITE -> R.layout.item_favorite_product_card_recycler
        ProductCardItemType.CART->R.layout.item_cart_product_card_recycler
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        return viewHolder
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        itemAdapter.differ.submitList(item.products)
    }
}
