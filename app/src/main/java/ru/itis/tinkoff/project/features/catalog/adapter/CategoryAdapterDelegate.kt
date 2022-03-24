package ru.itis.tinkoff.project.features.catalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ItemCategoryBinding
import ru.itis.tinkoff.project.features.catalog.model.Category
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem
import timber.log.Timber

class CategoryAdapterDelegate : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Category
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        Timber.tag("Scroll").d("CategoryAdapterDelegate createViewHolder")
        return CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
        /*categoryAdapterDelegate {

        }*/
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder: CategoryHolder = holder as CategoryHolder
        val category: Category = items[position] as Category

        Timber.tag("Scroll").d("CategoryAdapterDelegate bind  $position")
    }

    private fun categoryAdapterDelegate(
        itemClickedListener : (Category) -> Unit
    ): AdapterDelegate<List<DisplayableItem>> {
        val adapterDelegateViewBinding =
            adapterDelegateViewBinding<Category, DisplayableItem, ItemCategoryBinding>(
                { layoutInflater, root ->
                    ItemCategoryBinding.inflate(
                        layoutInflater,
                        root,
                        false)
                }
            ) {
                binding.tvCategoryName.setOnClickListener {
                    itemClickedListener(item)
                }
                bind {
                    binding.tvCategoryName.text = item.name
                }
            }
        return adapterDelegateViewBinding
    }
}