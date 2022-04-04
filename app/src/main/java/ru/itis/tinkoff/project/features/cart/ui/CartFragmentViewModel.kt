package ru.itis.tinkoff.project.features.cart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.cart.utils.CartItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CartFragmentViewModel(
    private val cartRepository: CartRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val _item = MutableStateFlow<List<CartItem>>(emptyList())
    private val itemProvider = CartItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow(0)
    val productsListSize = _productsListSize.asStateFlow()
    val item = _item.asStateFlow()


    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            val products = cartRepository.getProducts()
            val items = itemProvider.getItems(products)
            _productsListSize.value = products.size
            _item.value = items
        }
    }
}
