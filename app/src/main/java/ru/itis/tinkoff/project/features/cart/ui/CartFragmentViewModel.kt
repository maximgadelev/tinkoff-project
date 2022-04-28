package ru.itis.tinkoff.project.features.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.cart.utils.CartItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.common.ErrorEvent

@SuppressWarnings("MagicNumber")
class CartFragmentViewModel(
    private val cartRepository: CartRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {

    private val eventChannel = Channel<ErrorEvent>()
    private val _item = MutableStateFlow<List<CartItem>>(emptyList())
    private val itemProvider = CartItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow(0)
    private val _orderPrice = MutableStateFlow(0)
    private val _orderDiscount = MutableStateFlow(0)
    private val _orderTotalPrice = MutableStateFlow(0)
    val productsListSize = _productsListSize.asStateFlow()
    val item = _item.asStateFlow()
    val orderPrice = _orderPrice.asStateFlow()
    val orderDiscount = _orderDiscount.asStateFlow()
    val orderTotalPrice = _orderTotalPrice.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            try {
                val products = cartRepository.getProducts()
                val items = itemProvider.getItems(products)
                _productsListSize.value = products.size
                _item.value = items
                _orderPrice.value = products.sumOf { it.price.toInt() }
                _orderDiscount.value = 280
                _orderTotalPrice.value = _orderPrice.value - _orderDiscount.value
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
