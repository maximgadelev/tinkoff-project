package ru.itis.tinkoff.project.features.productPage.ui.renderer

import kotlinx.android.synthetic.main.item_charateristic.view.*
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class CharacteristicListRenderer<Item> :
    ItemBaseRenderer<Item, CharacteristicListRenderer.RenderContract>() {
    interface RenderContract {
        val type: String
        val characteristic: String
    }

    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.textView_characteriscType.text = item.type
            itemView.textView_characterisc.text = item.characteristic
        }
    }

    override val layoutRes: Int
        get() = R.layout.item_charateristic
}
