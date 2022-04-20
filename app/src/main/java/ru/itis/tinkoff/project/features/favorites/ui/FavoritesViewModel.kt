package ru.itis.tinkoff.project.features.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.common.utils.ErrorEvent
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItem

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<ErrorEvent>()
    private val _item = MutableStateFlow<List<FavoritesItem>>(emptyList())
    private val itemProvider = FavoritesItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow(0)
    val productsListSize = _productsListSize.asStateFlow()
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            try {
                val products = favoritesRepository.getProducts()
                val items = itemProvider.getItems(products)
                _productsListSize.value = products.size
                _item.value = items
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
