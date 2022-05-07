package ru.itis.tinkoff.project.features.main.ui.renderer

import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_title.view.*
import ru.haroncode.aquarius.core.clicker.ClickableRenderer
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class TitleRenderer<Item> : ItemBaseRenderer<Item, TitleRenderer.RenderContract>(),
    ClickableRenderer {

    interface RenderContract {
        @get:StringRes
        val titleRes: Int?
        val title: CharSequence?
            get() = null
    }

    override fun bindClickListener(
        viewHolder: RecyclerView.ViewHolder,
        listener: (RecyclerView.ViewHolder, View) -> Unit
    ) {
        viewHolder.itemView.setOnClickListener { listener(viewHolder, it) }
    }

    override fun unbindClickListener(viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.setOnClickListener(null)
    }
    override val layoutRes: Int = R.layout.item_title

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        val titleRes = item.titleRes
        val title = item.title

        viewHolder.itemView.title.text = when {
            titleRes != null -> viewHolder.itemView.context.getString(titleRes)
            else -> title
        }
    }
}
