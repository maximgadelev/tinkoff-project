package ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_category_catalog.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class CategoryListRenderer<Item> : ItemBaseRenderer<Item, CategoryListRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        val title: String
        val icon: Int
    }

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.setOnClickListener {
            listener(viewHolder, it)
        }
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.tv_category_name.text = item.title
            itemView.iv_icon_category.load(item.icon)
        }
    }

    override val layoutRes: Int = R.layout.item_category_catalog
}
