package ru.itis.tinkoff.project.features.promotionPage.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
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
import ru.itis.tinkoff.project.features.promotionPage.utils.PromotionPageItem

class PromotionPageFragment : Fragment(R.layout.promotion_page_fragment) {
    private val viewBinding by viewBinding(PromotionPageFragmentBinding::bind)
    private val viewModel: PromotionPageViewModel by viewModel<PromotionPageViewModel>()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<PromotionPageItem>()
            .renderer(
                PromotionPageItem.ProductListPromotionPageItem::class,
                ProductCardListRenderer(ProductCardItemType.FAVORITE,::onClickButton)
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
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.server_blocked)
        }.show()
    }
    private fun onClickButton(renderContract: ProductCardRenderer.RenderContract) {
        val bundle = Bundle()
        bundle.putInt("id", renderContract.id)
        findNavController()
            .navigate(R.id.action_promotionPageFragment_to_productPageFragment, bundle)
    }
}
