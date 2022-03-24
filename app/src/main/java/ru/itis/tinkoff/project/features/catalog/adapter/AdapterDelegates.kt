package ru.itis.tinkoff.project.features.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.tinkoff.project.databinding.ItemCategoryBinding
import ru.itis.tinkoff.project.features.catalog.model.Category
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem

object AdapterDelegates {

    val categoryAdapterDelegate = adapterDelegateViewBinding<Category, DisplayableItem, ItemCategoryBinding>(
        { layoutInflater, root ->
            ItemCategoryBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.categoryName = item.name
            binding.executePendingBindings()
        }
    }

}