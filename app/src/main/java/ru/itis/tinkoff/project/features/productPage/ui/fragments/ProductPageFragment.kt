package ru.itis.tinkoff.project.features.productPage.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.product_page_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProductPageFragmentBinding
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
        val pagerAdapter = PagerAdapter(this)
        viewModel.onViewCreated(1)
        with(recyclerView_images_product_page) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
        viewModel.item.onEach {
            itemAdapter.differ.submitList(it)
        }
            .launchIn(lifecycleScope)
        viewBinding.pager.adapter = pagerAdapter
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.pager) { tab, position ->
            if (position == 0) {
                tab.text = getString(R.string.description)
            } else {
                tab.text = getString(R.string.charactreristics)
            }
        }.attach()
    }
}
