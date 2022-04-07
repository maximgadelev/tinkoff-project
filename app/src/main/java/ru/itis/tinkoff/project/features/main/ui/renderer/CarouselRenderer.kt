package ru.itis.tinkoff.project.features.main.ui.renderer

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
import ru.itis.tinkoff.project.features.main.ui.renderer.CarouselRenderer.RenderContract
import ru.itis.tinkoff.project.features.main.utils.PromotionItemSize

class CarouselRenderer<Item>(size: PromotionItemSize) : ItemBaseRenderer<Item, RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val promotions: List<Promotion>
    }

    data class Promotion(
        override val image: String,
        override val name: String
    ) : PromotionRender.RenderContract, ComparableItem

    private val itemAdapter by lazy {
        RenderAdapterBuilder<Promotion>()
            .renderer(Promotion::class, PromotionRender(size))
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

    override val layoutRes: Int = R.layout.item_carousel

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        val viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        return viewHolder
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        itemAdapter.differ.submitList(item.promotions)
    }
}
