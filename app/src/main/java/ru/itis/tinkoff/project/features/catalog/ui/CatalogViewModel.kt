package ru.itis.tinkoff.project.features.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.catalog.data.CatalogRepository
import ru.itis.tinkoff.project.features.catalog.utils.CategoryItem
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CatalogViewModel(
    private val repository: CatalogRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<CategoryItem>>(emptyList())
    private val itemProvider = CatalogItemProvider(entityMapper)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()

    init {
        onViewCreated()
    }

    fun onViewCreated() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val categories = repository.getCategories()
                val items = itemProvider.getItemList(categories)
                _item.value = items
                _isLoading.value = false
            } catch (ex: Exception) {
                eventChannel.send(Event.ExceptionEvent)
                _isLoading.value = false
            }
        }
    }
}
