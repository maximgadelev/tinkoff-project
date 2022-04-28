package ru.itis.tinkoff.project.features.catalog.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer.CategoryRenderer
import ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer.CatalogRenderer
import ru.itis.tinkoff.project.features.catalog.utils.CatalogListItem

class CatalogFragment : Fragment(R.layout.catalog_fragment) {

    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)
    private val viewModel: CategoryViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<CatalogListItem>()
            .renderer(CatalogListItem.CategoryListItem::class, CategoryRenderer())
            .renderer(CatalogListItem::class, CatalogRenderer())
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
    }

    private fun initList() {
        with(viewBinding) {
            rvCatalog.adapter = itemAdapter
        }
    }
}


