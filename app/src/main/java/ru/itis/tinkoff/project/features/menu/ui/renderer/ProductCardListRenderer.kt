package ru.itis.tinkoff.project.features.menu.ui.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import java.math.BigDecimal


class ProductCardListRenderer<Item> :
    ItemBaseRenderer<Item, ProductCardListRenderer.RenderContract>(), ClickableRenderer {

    interface RenderContract {
        val products: List<Product>
    }

    data class Product(
        override val name: String,
        override val image: String,
        override val price: BigDecimal
    ) : ProductCardRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Product>()
            .renderer(Product::class, ProductCardRenderer())
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

    override val layoutRes: Int = R.layout.item_product_card_recycler

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