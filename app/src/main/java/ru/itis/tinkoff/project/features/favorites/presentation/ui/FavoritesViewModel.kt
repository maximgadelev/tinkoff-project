package ru.itis.tinkoff.project.features.favorites.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItem
import ru.itis.tinkoff.project.features.favorites.utils.FavoritesItemProvider

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val _item = MutableStateFlow<List<FavoritesItem>>(emptyList())
    val item = _item.asStateFlow()
    private val itemProvider = FavoritesItemProvider(entityMapper)
    private val _productsListSize = MutableStateFlow<Int>(0)
    val productsListSize = _productsListSize.asStateFlow()

    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            val products = favoritesRepository.getProducts()
            val items = itemProvider.getItems(products)
            _productsListSize.value=products.size
            _item.value = items
        }
    }
}