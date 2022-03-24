package ru.itis.tinkoff.project.features.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.databinding.ItemCategoryBinding
import ru.itis.tinkoff.project.features.catalog.model.Category
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem
import ru.itis.tinkoff.project.features.catalog.repository.CategoryRepository

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

    /*val catalogAdapterDelegate = adapterDelegateViewBinding<Category, DisplayableItem, CatalogFragmentBinding>(
        { layoutInflater, root ->
            CatalogFragmentBinding.inflate(
                layoutInflater,
                root,
                false
            ).apply {
                rvCatalog.adapter = categoryAdapter
            }
        }
    ) {     bind {
                (binding.rvCatalog.adapter as ListDelegationAdapter<*>).apply {
                    items = CategoryRepository.categories
                }
            }
    }

    private val categoryAdapter = ListDelegationAdapter(
        categoryAdapterDelegate
    )*/
}