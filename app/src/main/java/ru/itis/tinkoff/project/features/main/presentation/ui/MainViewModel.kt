package ru.itis.tinkoff.project.features.main.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.presentation.mapper.EntityMapper
import ru.itis.tinkoff.project.features.main.utils.MenuItem
import ru.itis.tinkoff.project.features.main.utils.MenuItemProvider

class MainViewModel(
    private val menuRepository: MenuRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val _item = MutableStateFlow<List<MenuItem>>(emptyList())
    val item = _item.asStateFlow()
    private val itemProvider = MenuItemProvider(entityMapper)

    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            val promotions = menuRepository.getPromotions()
            val products = menuRepository.getProducts()
            val items = itemProvider.getItemList(products, promotions)
            _item.value = items
        }
    }
}
