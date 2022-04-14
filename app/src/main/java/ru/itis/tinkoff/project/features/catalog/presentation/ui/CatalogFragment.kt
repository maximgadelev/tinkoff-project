package ru.itis.tinkoff.project.features.catalog.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer.CategoryListRenderer
import ru.itis.tinkoff.project.features.catalog.presentation.ui.utils.CategoryItemProvider
import ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel.CategoryViewModel

class CatalogFragment : Fragment(R.layout.catalog_fragment) {

    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)
    //private val viewModel: CategoryViewModel by viewModel()
    private val categoryItemProvider = CategoryItemProvider()

    private val adapter by lazy {
        RenderAdapterBuilder<Category>()
            .renderer(Category::class, CategoryListRenderer())
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init () {
        with(viewBinding) {
            rvCatalog.adapter = adapter
        }
        adapter.differ.submitList(categoryItemProvider.getItemList(context))
    }
}


