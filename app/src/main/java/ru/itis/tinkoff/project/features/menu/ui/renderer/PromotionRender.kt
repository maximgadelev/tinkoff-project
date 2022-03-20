package ru.itis.tinkoff.project.features.menu.ui.renderer


import com.bumptech.glide.Glide
import ru.haroncode.aquarius.core.renderer.ItemBaseRenderer
import ru.itis.tinkoff.project.R

import kotlinx.android.synthetic.main.item_promotion_square_200dp.view.*
import kotlinx.android.synthetic.main.item_promotion_long.view.*
import kotlinx.android.synthetic.main.item_promotion_square_100dp.view.*
import ru.itis.tinkoff.project.features.menu.ui.renderer.PromotionRender.RenderContract
class PromotionRender<Item>(isLong:String) : ItemBaseRenderer<Item, RenderContract>() {
    interface RenderContract {
        val image: String
        val name:String
    }

    override val layoutRes: Int
       =when(isLong) {
        "Long" -> R.layout.item_promotion_long
        "Square_200"->R.layout.item_promotion_square_200dp
        "Square_100"->R.layout.item_promotion_square_100dp
        else -> 1
    }




    override fun onBindView(viewHolder: BaseViewHolder, item: RenderContract) {
        if (layoutRes==R.layout.item_promotion_long) {
            Glide.with(viewHolder.itemView)
                .load(item.image)
                .into(viewHolder.itemView.imageView_long)
        }else if(layoutRes==R.layout.item_promotion_square_200dp){
            Glide.with(viewHolder.itemView)
                .load(item.image)
                .into(viewHolder.itemView.imageView_square_200)
        }else if(layoutRes==R.layout.item_promotion_square_100dp){
            Glide.with(viewHolder.itemView)
                .load(item.image)
                .into(viewHolder.itemView.imageView_square_100)
        }
    }
}