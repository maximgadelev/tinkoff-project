package ru.itis.tinkoff.project.features.productPage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.entity.Product
import ru.itis.tinkoff.project.features.common.ErrorEvent
import ru.itis.tinkoff.project.features.productPage.data.ProductPageRepository
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageViewModel(
    private val productPageRepository: ProductPageRepository,
) : ViewModel() {
    private val eventChannel = Channel<ErrorEvent>()
    private val _item = MutableStateFlow<List<ProductPageItem>>(emptyList())
    private val itemProvider = ProductPageItemProvider()
    private val _mainProduct =
        MutableSharedFlow<Product>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val item = _item.asStateFlow()
    val mainProduct = _mainProduct.asSharedFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    fun onViewCreated(id: Int) {
        viewModelScope.launch {
            try {
                val product = productPageRepository.getProductById(id)
                val list = itemProvider.getItems(product)
                _mainProduct.emit(product)
                _item.value = list
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
