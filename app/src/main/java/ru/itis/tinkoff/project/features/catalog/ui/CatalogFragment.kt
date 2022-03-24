package ru.itis.tinkoff.project.features.catalog.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.features.catalog.adapter.AdapterDelegates
import ru.itis.tinkoff.project.features.catalog.repository.CategoryRepository

class CatalogFragment : Fragment(R.layout.catalog_fragment) {

    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)
    private val adapter = ListDelegationAdapter (
        AdapterDelegates.categoryAdapterDelegate
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init () {
        with(viewBinding) {
            rvCatalog.adapter = adapter
            adapter.apply {
                items = CategoryRepository.categories
                notifyDataSetChanged()
            }
        }
    }
}
