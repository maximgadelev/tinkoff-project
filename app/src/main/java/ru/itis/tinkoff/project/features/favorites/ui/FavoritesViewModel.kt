package ru.itis.tinkoff.project.features.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItem

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository,
    private val cartRepository: CartRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<FavoritesItem>>(emptyList())
    private val itemProvider = FavoritesItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow(0)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    val productsListSize = _productsListSize.asStateFlow()
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    fun onViewCreated() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val products = favoritesRepository.getProducts()
                val items = itemProvider.getItems(products)
                _productsListSize.value = products.size
                _item.value = items
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
                cartRepository.addProductToCart(id, quality)
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
