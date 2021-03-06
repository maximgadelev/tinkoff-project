package ru.itis.tinkoff.project.features.promotionPage.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import kotlinx.android.synthetic.main.promotion_page_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.PromotionPageFragmentBinding
import ru.itis.tinkoff.project.features.common.ProductCardItemType
import ru.itis.tinkoff.project.features.common.renderer.ProductCardListRenderer
import ru.itis.tinkoff.project.features.common.renderer.ProductCardRenderer
import ru.itis.tinkoff.project.features.common.utils.ExceptionDialogFragment
import ru.itis.tinkoff.project.features.promotionPage.utils.PromotionPageItem

class PromotionPageFragment : Fragment(R.layout.promotion_page_fragment) {
    private val viewBinding by viewBinding(PromotionPageFragmentBinding::bind)
    private val viewModel: PromotionPageViewModel by viewModel<PromotionPageViewModel>()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<PromotionPageItem>()
            .renderer(
                PromotionPageItem.ProductListPromotionPageItem::class,
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
        val id = arguments?.getInt("id")
        id?.let { viewModel.onViewCreated(it) }
        createProductList()
        viewBinding.ImageViewTitle.load(arguments?.getString("image"))
        createMainInformation()
        showOrHideLoading()
        id?.let { refreshFragment(it) }
    }

    private fun createProductList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun createMainInformation() {
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun showDialog() {
        val dialog = ExceptionDialogFragment()
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun onClickButton(renderContract: ProductCardRenderer.RenderContract, view: View) {
        when (view.id) {
            R.id.buttonToCardFavorite ->
                view.visibility = View.GONE
            R.id.imageButton_plusQuantity -> {
            }
            R.id.imageButton_minusQuantity -> {
            }
            R.id.textViewQuantity -> {
                val quanityTextView = view as TextView
                viewModel.onAddProductToCart(
                    renderContract.id, quanityTextView.text.toString().toInt()
                )
            }
            else -> {
                val bundle = Bundle()
                bundle.putInt("id", renderContract.id)
                findNavController()
                    .navigate(R.id.action_promotionPageFragment_to_productPageFragment, bundle)
            }
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

    private fun refreshFragment(id: Int) {
        viewBinding.refreshLayout.setOnRefreshListener {
            viewModel.onViewCreated(id)
            viewBinding.refreshLayout.isRefreshing = false
        }
    }
}
