package ru.itis.tinkoff.project.features.catalog.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.itis.tinkoff.project.databinding.ItemCategoryBinding
import ru.itis.tinkoff.project.features.catalog.model.Category

class CategoryHolder(
    item: View
) : RecyclerView.ViewHolder(item) {
    private var category: Category? = null
    val binding = ItemCategoryBinding.bind(item)

    fun bind(item: Category) {
        this.category = item
        with(binding) {
            tvCategoryName.text = item.name
            ivIconCategory.setImageResource(item.photo)
        }

        itemView.setOnClickListener {

        }
    }
}