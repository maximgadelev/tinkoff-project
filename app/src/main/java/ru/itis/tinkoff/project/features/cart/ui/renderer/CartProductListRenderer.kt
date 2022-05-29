package ru.itis.tinkoff.project.features.cart.ui.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic

class CartProductListRenderer<Item>(
    private val listener: (CartProductRenderer.RenderContract) -> Unit
) :
    ItemBaseRenderer<Item, CartProductListRenderer.RenderContract>(), ClickableRenderer {
    interface RenderContract {
        val products: List<Product>
    }

    data class Product(
        override val id: Int,
        override val name: String,
        override val image: List<String>,
        override val characteristics: List<Characteristic>,
        override val price: String,
        override val description: String,
        override val company: String,
        override val quantity: String
    ) : CartProductRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<CartProductListRenderer.Product>()
            .renderer(
                CartProductListRenderer.Product::class,
                CartProductRenderer(),
                DefaultClicker(listener)
            )
            .build(DifferStrategies.withDiffUtilComparable())
    }
    override val layoutRes: Int
        get() = R.layout.item_cart_product_card_recycler

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        return viewHolder
    }

    override fun onBindView(
        viewHolder: BaseViewHolder,
        item: CartProductListRenderer.RenderContract
    ) {
        itemAdapter.differ.submitList(item.products)
    }
}
