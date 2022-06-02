package ru.itis.tinkoff.project.features.common.renderer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic
import ru.itis.tinkoff.project.features.common.ProductCardItemType
import ru.itis.tinkoff.project.features.common.utils.CustomClicker

class ProductCardListRenderer<Item>(
    type: ProductCardItemType,
    listener: (ProductCardRenderer.RenderContract, View) -> Unit,
) : ItemBaseRenderer<Item, ProductCardListRenderer.RenderContract>() {

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
    ) : ProductCardRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Product>()
            .renderer(Product::class, ProductCardRenderer(type), CustomClicker(listener))
            .build(DifferStrategies.withDiffUtilComparable())
    }
    override val layoutRes: Int = when (type) {
        ProductCardItemType.MAIN -> R.layout.item_product_card_recycler
        ProductCardItemType.FAVORITE -> R.layout.item_favorite_product_card_recycler
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
