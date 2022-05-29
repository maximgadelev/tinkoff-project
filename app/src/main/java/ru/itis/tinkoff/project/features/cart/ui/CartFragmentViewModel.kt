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
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

@SuppressWarnings("MagicNumber")
class CartFragmentViewModel(
    private val cartRepository: CartRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {

    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<CartItem>>(emptyList())
    private val itemProvider = CartItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow(0)
    private val _orderPrice = MutableStateFlow(0)
    private val _orderDiscount = MutableStateFlow(0)
    private val _orderTotalPrice = MutableStateFlow(0)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val productsListSize = _productsListSize.asStateFlow()
    val item = _item.asStateFlow()
    val orderPrice = _orderPrice.asStateFlow()
    val orderDiscount = _orderDiscount.asStateFlow()
    val orderTotalPrice = _orderTotalPrice.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()
    val isLoading = _isLoading.asStateFlow()

    init {
        onViewCreated()
    }

    fun onViewCreated() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val products = cartRepository.getProducts().cartProducts
                val items = itemProvider.getItems(products)
                _productsListSize.value = products.size
                _item.value = items
                _orderPrice.value = cartRepository.getTotalSum()
                _orderDiscount.value = 0
                _orderTotalPrice.value = _orderPrice.value - _orderDiscount.value
                _isLoading.value = false
            } catch (ex: Exception) {
                _isLoading.value = false
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
