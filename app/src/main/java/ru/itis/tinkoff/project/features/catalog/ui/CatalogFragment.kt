package ru.itis.tinkoff.project.features.catalog.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.reviews_page_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding
import ru.itis.tinkoff.project.features.catalog.ui.renderer.CategoryListRenderer
import ru.itis.tinkoff.project.features.catalog.ui.renderer.CategoryRenderer
import ru.itis.tinkoff.project.features.catalog.utils.CategoryItem
import ru.itis.tinkoff.project.features.common.utils.ExceptionDialogFragment

class CatalogFragment : Fragment(R.layout.catalog_fragment) {

    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)
    private val viewModel: CatalogViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<CategoryItem>().renderer(
            CategoryItem.CategoryListItem::class,
            CategoryListRenderer(::onClickCategory)
        ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createList()
        createMainInformation()
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect {
                showDialog()
            }
        }
        refreshFragment()
        showOrHideLoading()
    }

    private fun showDialog() {
        val dialog = ExceptionDialogFragment()
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun refreshFragment() {
        viewBinding.refreshLayout.setOnRefreshListener {
            viewModel.onViewCreated()
            viewBinding.refreshLayout.isRefreshing = false
        }
    }

    private fun createList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun showOrHideLoading() {
        viewModel.isLoading.onEach {
            if (it) {
                viewBinding.progress.visibility = View.VISIBLE
            } else {
                viewBinding.progress.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)
    }

    private fun createMainInformation() {
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun onClickCategory(contract: CategoryRenderer.RenderContract) {
        Toast.makeText(context, "опа", Toast.LENGTH_LONG).show()
    }
}
