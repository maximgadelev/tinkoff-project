package ru.itis.tinkoff.project.features.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.FavoritesFragmentBinding
import ru.itis.tinkoff.project.features.ExceptionDialogFragment
import ru.itis.tinkoff.project.features.common.ProductCardItemType
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardRenderer
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItem

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    private val viewBinding by viewBinding(FavoritesFragmentBinding::bind)
    private val viewModel: FavoritesViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<FavoritesItem>()
            .renderer(
                FavoritesItem.ProductListFavoritesItem::class,
                ProductCardListRenderer(ProductCardItemType.FAVORITE, ::onClickButton)
            ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect {
                showDialog()
            }
        }
        createFavoritesProductList()
        createMainInformation()
        showOrHideLoading()
        refreshFragment()
    }

    private fun createFavoritesProductList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun createMainInformation() {
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
        viewModel.productsListSize.onEach {
            viewBinding.textViewProducts.text = getString(R.string.number_of_products, it)
        }
            .launchIn(lifecycleScope)
    }

    private fun showDialog() {
        val dialog = ExceptionDialogFragment()
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun onClickButton(renderContract: ProductCardRenderer.RenderContract, view: View) {
        if (view.id == R.id.buttonToCardFavorite) {
            view.visibility = View.GONE
            viewModel.onAddProductToCart(renderContract.id, 1)
        } else {
            val bundle = Bundle()
            bundle.putInt("id", renderContract.id)
            findNavController()
                .navigate(R.id.action_favourites_to_productPageFragment, bundle)
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

    private fun refreshFragment() {
        viewBinding.refreshLayout.setOnRefreshListener {
            viewModel.onViewCreated()
            viewBinding.refreshLayout.isRefreshing = false
        }
    }
}
