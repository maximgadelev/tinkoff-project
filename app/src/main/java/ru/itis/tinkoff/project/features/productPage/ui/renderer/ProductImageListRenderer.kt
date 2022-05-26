package ru.itis.tinkoff.project.features.productPage.ui.renderer

import coil.load
import kotlinx.android.synthetic.main.item_product_page_image.view.*
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

class ProductImageListRenderer<Item> :
    ItemBaseRenderer<Item, ProductImageListRenderer.RenderContract>() {
    interface RenderContract {
        val image: String
    }
    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        with(viewHolder) {
            itemView.imageView_product_page.load(item.image)
        }
    }

    override val layoutRes: Int
        get() = R.layout.item_product_page_image
}
