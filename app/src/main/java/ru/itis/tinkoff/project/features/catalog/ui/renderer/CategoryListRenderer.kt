package ru.itis.tinkoff.project.features.catalog.ui.renderer

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

class CategoryListRenderer<Item>(
    private val listener: (CategoryRenderer.RenderContract) -> Unit
) : ItemBaseRenderer<Item, CategoryListRenderer.RenderContract>(), ClickableRenderer {
    interface RenderContract {
        val categories: List<Category>
    }

    data class Category(
        override val id: Int,
        override val name: String,
        override val image: String,
    ) : CategoryRenderer.RenderContract, ComparableItem

    override val layoutRes: Int
        get() = R.layout.item_category_list

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Category>()
            .renderer(Category::class, CategoryRenderer(), DefaultClicker(listener))
            .build(DifferStrategies.withDiffUtilComparable())
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
        itemAdapter.differ.submitList(item.categories)
    }
}
