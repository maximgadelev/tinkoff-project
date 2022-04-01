package ru.itis.tinkoff.project.features.favorites.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeId
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.haroncode.aquarius.core.clicker.DefaultClicker
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.FavoritesFragmentBinding
import ru.itis.tinkoff.project.features.common.ProductCardItemType
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItem

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    private val viewBinding by viewBinding(FavoritesFragmentBinding::bind)
    private val viewModel: FavoritesViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<FavoritesItem>()
            .renderer(
                FavoritesItem.ProductListFavoritesItem::class,
                ProductCardListRenderer(ProductCardItemType.FAVORITE)
            ).build(DifferStrategies.withDiffUtilComparable())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createFavoritesProductList()
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
        viewModel.productsListSize.onEach {
            viewBinding.textViewProducts.text = getString(R.string.number_of_products, it)
        }
            .launchIn(lifecycleScope)
    }

    private fun createFavoritesProductList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }
}

