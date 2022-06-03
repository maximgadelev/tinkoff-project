package ru.itis.tinkoff.project.features.productPage.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.count_button_product_page_view.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProductPageFragmentBinding
import ru.itis.tinkoff.project.features.common.utils.ExceptionDialogFragment
import ru.itis.tinkoff.project.features.productPage.ui.ProductPageViewModel
import ru.itis.tinkoff.project.features.productPage.ui.adapter.PagerAdapter
import ru.itis.tinkoff.project.features.productPage.ui.renderer.ProductImageListRenderer
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageFragment : Fragment(R.layout.product_page_fragment) {
    private val viewBinding by viewBinding(ProductPageFragmentBinding::bind)
    private val viewModel: ProductPageViewModel by viewModel<ProductPageViewModel>()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ProductPageItem>().renderer(
            ProductPageItem.ProductImageListItem::class,
            ProductImageListRenderer()
        ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        id?.let { viewModel.onViewCreated(it) }
        createMainInformation()
        showOrHideLoading()
        id?.let { onCreateButtonToCart(it) }
        id?.let { refreshFragment(it) }
    }

    private fun createMainInformation() {
        viewBinding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.mainProduct.onEach {
            viewBinding.recyclerViewImagesProductPage.adapter = itemAdapter
            viewBinding.textViewProductPagePrice.text =
                getString(R.string.price_in_ruble, it.price.toInt())
            onClickToReview(it.id, it.rating)
            viewBinding.textViewProductPagePriceSecond.text =
                getString(R.string.price_in_ruble, it.price.toInt())
            viewBinding.textViewProductBrand.text = it.companyName
            viewBinding.textViewProductName.text = it.name
            viewBinding.pager.adapter =
                PagerAdapter(this, it.description, it.characteristic)
            TabLayoutMediator(viewBinding.tabLayout, viewBinding.pager) { tab, position ->
                if (position == 0) {
                    tab.text = getString(R.string.description)
                } else {
                    tab.text = getString(R.string.charactreristics)
                }
            }.attach()
        }.launchIn(lifecycleScope)
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
        viewModel.eventFlow.onEach {
            showDialog()
        }.launchIn(lifecycleScope)
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

    private fun refreshFragment(id: Int) {
        viewBinding.refreshLayout.setOnRefreshListener {
            viewModel.onViewCreated(id)
            viewBinding.refreshLayout.isRefreshing = false
        }
    }

    private fun showDialog() {
        val dialog = ExceptionDialogFragment()
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun onClickToReview(id: Int, rating: Double) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putDouble("rating", rating)
        viewBinding.buttonToFeedback.setOnClickListener {
            findNavController().navigate(R.id.action_productPageFragment_to_reviewsFragment, bundle)
        }
    }

    private fun onCreateButtonToCart(id: Int) {
        viewBinding.buttonAddToCart.setOnClickListener {
            viewBinding.buttonAddToCart.visibility = View.GONE
            viewBinding.countButton.visibility = View.VISIBLE
            viewModel.onAddProductToCart(id, viewBinding.countButton.getCount())
            viewBinding.countButton.imageButton_plusQuantity.setOnClickListener {
                viewBinding.countButton.setCount(viewBinding.countButton.getCount() + 1)
                viewModel.onAddProductToCart(id, viewBinding.countButton.getCount())
            }
            viewBinding.countButton.imageButton_minusQuantity.setOnClickListener {
                viewBinding.countButton.setCount(viewBinding.countButton.getCount() - 1)
                viewModel.onAddProductToCart(id, viewBinding.countButton.getCount())
            }
        }
    }
}
