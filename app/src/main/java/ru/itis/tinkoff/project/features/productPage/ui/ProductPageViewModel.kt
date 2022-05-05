package ru.itis.tinkoff.project.features.productPage.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.ErrorEvent
import ru.itis.tinkoff.project.features.productPage.data.ProductPageRepository
import ru.itis.tinkoff.project.features.productPage.utils.ProductPageItem

class ProductPageViewModel(
    private val productPageRepository: ProductPageRepository,
) : ViewModel() {
    private val eventChannel = Channel<ErrorEvent>()
    private val _item = MutableStateFlow<List<ProductPageItem>>(emptyList())
    private val itemProvider = ProductPageItemProvider()
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

     fun onViewCreated(id: Int) {
        viewModelScope.launch {
            try {
                val product = productPageRepository.getProductById(id)
                val list = itemProvider.getItems(product)
                _item.value = list
                Log.e("123",_item.value.toString())
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
