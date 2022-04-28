package ru.itis.tinkoff.project.features.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.common.ErrorEvent
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.utils.MenuItem

class MainViewModel(
    private val menuRepository: MenuRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<ErrorEvent>()
    private val _item = MutableStateFlow<List<MenuItem>>(emptyList())
    private val itemProvider = MenuItemProvider(entityMapper)
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModelScope.launch {
            try {
                val promotions = menuRepository.getPromotions()
                val products = menuRepository.getProducts()
                val items = itemProvider.getItemList(products, promotions)
                _item.value = items
            } catch (ex: Exception) {
                eventChannel.send(ErrorEvent)
            }
        }
    }
}
