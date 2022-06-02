package ru.itis.tinkoff.project.features.promotionPage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.promotionPage.data.PromotionRepository
import ru.itis.tinkoff.project.features.promotionPage.utils.PromotionPageItem

class PromotionPageViewModel(
    private val promotionPageRepository: PromotionRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<PromotionPageItem>>(emptyList())
    private val itemProvider = PromotionPageItemProvider(entityMapper)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()
    val item = _item.asStateFlow()

    fun onViewCreated(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val products = promotionPageRepository.getProducts(id)
                val items = itemProvider.getItems(products)
                _item.value = items
                _isLoading.value = false
            } catch (ex: Exception) {
                _isLoading.value = false
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
