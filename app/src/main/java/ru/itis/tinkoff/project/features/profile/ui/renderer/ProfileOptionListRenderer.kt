package ru.itis.tinkoff.project.features.profile.ui.renderer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_option.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ProfileOptionListRenderer<Item> :
    ItemBaseRenderer<Item, ProfileOptionListRenderer.RenderContract>(), ClickableRenderer {

    interface RenderContract {
        val title: Int
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
            itemView.tv_option_title.text = itemView.context?.resources?.getString(item.title)
            itemView.iv_icon_option.load(item.icon)
        }
    }

    override val layoutRes: Int = R.layout.item_option
}
