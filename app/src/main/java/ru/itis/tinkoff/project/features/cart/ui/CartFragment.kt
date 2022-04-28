package ru.itis.tinkoff.project.features.cart.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CartFragmentBinding
import ru.itis.tinkoff.project.features.cart.ui.renderer.CartProductListRenderer
import ru.itis.tinkoff.project.features.cart.utils.CartItem

class CartFragment : Fragment(R.layout.cart_fragment) {

    private val viewBinding by viewBinding(CartFragmentBinding::bind)
    private val viewModel: CartFragmentViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<CartItem>()
            .renderer(
                CartItem.ProductListCartItem::class,
                CartProductListRenderer()
            ).build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.eventFlow.collect {
                showDialog()
            }
        }
        createCartProductsList()
        createCartMainInformation()
    }

    private fun createCartProductsList() {
        with(recyclerView) {
            setHasFixedSize(true)
            adapter = itemAdapter
        }
    }

    private fun createCartMainInformation() {
        with(viewModel) {
            item.onEach {
                itemAdapter.differ.submitList(it)
            }
                .launchIn(lifecycleScope)
            productsListSize.onEach {
                viewBinding.textViewProducts.text = getString(R.string.number_of_products, it)
            }
                .launchIn(lifecycleScope)
            orderPrice.onEach {
                viewBinding.orderPriceCountTextView.text = getString(R.string.price_in_ruble, it)
            }
                .launchIn(lifecycleScope)
            orderDiscount.onEach {
                viewBinding.discountCountTextView.text = getString(R.string.discount_in_ruble, it)
            }
                .launchIn(lifecycleScope)
            orderTotalPrice.onEach {
                viewBinding.totalPriceCountTextView.text = getString(R.string.price_in_ruble, it)
            }
                .launchIn(lifecycleScope)
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.server_blocked)
        }.show()
    }
}
