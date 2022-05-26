package ru.itis.tinkoff.project.features.common.renderer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Characteristic
import ru.itis.tinkoff.project.features.common.ProductCardItemType

@SuppressWarnings("LateinitUsage")

class ProductCardListRenderer<Item>(
    type: ProductCardItemType,
    listener: (ProductCardRenderer.RenderContract) -> Unit
) : ItemBaseRenderer<Item, ProductCardListRenderer.RenderContract>() {
    private lateinit var viewHolder: RecyclerView.ViewHolder

    interface RenderContract {
        @get:IdRes
        val id: Int?
            get() = null
        val products: List<Product>
    }

    data class Product(
        override val id: Int,
        override val name: String,
        override val image: List<String>,
        override val characteristics: List<Characteristic>,
        override val price: String,
        override val description: String,
        override val company: String
    ) : ProductCardRenderer.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Product>()

            .renderer(Product::class, ProductCardRenderer(type), DefaultClicker(listener))
            .build(DifferStrategies.withDiffUtilComparable())
    }
    override val layoutRes: Int = when (type) {
        ProductCardItemType.MAIN -> R.layout.item_product_card_recycler
        ProductCardItemType.FAVORITE -> R.layout.item_favorite_product_card_recycler
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        return viewHolder as BaseViewHolder
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        itemAdapter.differ.submitList(item.products)
    }

    private fun onClickButton(renderContract: ProductCardRenderer.RenderContract) {
        val bundle = Bundle()
        bundle.putInt("id", renderContract.id)
        viewHolder.itemView.findNavController()
            .navigate(R.id.action_menu_to_productPageFragment, bundle)
    }
}
