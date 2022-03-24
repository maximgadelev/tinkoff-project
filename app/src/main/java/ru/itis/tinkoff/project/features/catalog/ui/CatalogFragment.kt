package ru.itis.tinkoff.project.features.catalog.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.features.catalog.adapter.MainAdapter
import ru.itis.tinkoff.project.features.catalog.repository.CategoryRepository

class CatalogFragment : Fragment(R.layout.catalog_fragment) {

    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding
        init()
    }

    private fun init () {
        with(viewBinding) {
            val adapter = MainAdapter()
            adapter.items = CategoryRepository.categories
            rvCatalog.adapter = adapter
        }
    }
}
