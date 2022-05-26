package ru.itis.tinkoff.project.features.main.ui.renderer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.item_carousel.view.*
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.common.renderer.PromotionRender
import ru.itis.tinkoff.project.features.main.ui.MainFragment
import ru.itis.tinkoff.project.features.main.ui.renderer.SnapRenderer.RenderContract
import ru.itis.tinkoff.project.features.main.utils.PromotionItemSize

@SuppressWarnings("LateinitUsage")
class SnapRenderer<Item>(
    size: PromotionItemSize,
    private val isSnap: Boolean,
    private val listener: (PromotionRender.RenderContract) -> Unit
) : ItemBaseRenderer<Item, RenderContract>(), ClickableRenderer {
    private lateinit var viewHolder: RecyclerView.ViewHolder

    interface RenderContract {
        val promotions: List<Promotion>
    }

    data class Promotion(
        override val id: Int,
        override val name: String,
        override val isActive: Boolean,
        override val image: String,
        override val products: List<Product>,
    ) : PromotionRender.RenderContract, ComparableItem

    val itemAdapter by lazy {
        RenderAdapterBuilder<Promotion>()
            .renderer(Promotion::class, PromotionRender(size), DefaultClicker(listener))
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

    override val layoutRes: Int =
        if (isSnap) {
            R.layout.item_snap
        } else {
            R.layout.item_static_three_promotion_list
        }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder {
        viewHolder = super.onCreateViewHolder(inflater, parent)
        val recyclerView = viewHolder.itemView.recyclerView
        if (recyclerView.adapter == null) {
            recyclerView.adapter = itemAdapter
        }
        if (isSnap) {
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
        }
        return viewHolder as BaseViewHolder
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        itemAdapter.differ.submitList(item.promotions)
    }

    fun onClickButton(renderContract: PromotionRender.RenderContract) {
        val bundle = Bundle()
        bundle.putInt("id", renderContract.id)
        bundle.putString("image", renderContract.image)
        viewHolder.itemView.findNavController()
            .navigate(R.id.action_menu_to_promotionPageFragment, bundle)
    }
}
