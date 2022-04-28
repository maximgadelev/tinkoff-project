package ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_category_catalog.view.*
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class CategoryRenderer<Item> : ItemBaseRenderer<Item, CategoryRenderer.RenderContract>() {

    interface RenderContract {
        val name: String
        val icon: String
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.tv_category_name.text = item.name
            itemView.iv_icon_category.load(item.icon)
        }
    }

    override val layoutRes: Int = R.layout.item_category_catalog
}
