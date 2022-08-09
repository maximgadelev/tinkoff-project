package ru.itis.tinkoff.project.features.catalog.ui.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_category.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class CategoryRenderer<Item> : ItemBaseRenderer<Item, CategoryRenderer.RenderContract>(),
    ClickableRenderer {
    interface RenderContract {
        val id: Int
        val name: String
        val image: String
    }

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.setOnClickListener { listener(viewHolder, it) }
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        viewHolder.itemView.iv_icon_category.load(item.image)
    }

    override val layoutRes: Int
        get() = R.layout.item_category
}
