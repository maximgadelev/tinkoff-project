package ru.itis.tinkoff.project.features.profile.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_option.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ProfileOptionRenderer<Item> :
    ItemBaseRenderer<Item, ProfileOptionRenderer.RenderContract>(), ClickableRenderer {

    interface RenderContract {
        val title: String
        val icon: Int
    }

    override val layoutRes: Int = R.layout.item_option

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.iv_icon_option.load(item.icon)
            itemView.tv_option_title.text = item.title
        }
    }
}
