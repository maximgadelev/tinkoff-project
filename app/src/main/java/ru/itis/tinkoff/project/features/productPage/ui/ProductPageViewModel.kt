package ru.itis.tinkoff.project.features.productPage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.productPage.data.ProductPageRepository
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageViewModel(
    private val productPageRepository: ProductPageRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<ProductPageItem>>(emptyList())
    private val itemProvider = ProductPageItemProvider()
    private val _mainProduct =
        MutableSharedFlow<Product>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    val item = _item.asStateFlow()
    val mainProduct = _mainProduct.asSharedFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    fun onViewCreated(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val product = productPageRepository.getProductById(id)
                val list = itemProvider.getItems(product)
                _mainProduct.emit(product)
                _item.value = list
                _isLoading.value = false
            } catch (ex: Exception) {
                _isLoading.value = false
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }

    fun onAddProductToCart(id: Int, quality: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                cartRepository.addProductToCart(id, quality)
                _isLoading.value = false
            } catch (ex: Exception) {
                _isLoading.value = false
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
