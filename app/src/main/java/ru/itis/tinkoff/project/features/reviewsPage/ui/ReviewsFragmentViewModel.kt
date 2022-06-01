package ru.itis.tinkoff.project.features.reviewsPage.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.reviewsPage.data.ReviewRepository
import ru.itis.tinkoff.project.features.reviewsPage.utils.ReviewsItem
import java.lang.Exception

class ReviewsFragmentViewModel(
    private val reviewRepository: ReviewRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val eventChannel = Channel<Event>()
    private val _item = MutableStateFlow<List<ReviewsItem>>(emptyList())
    private val itemProvider = ReviewsItemProvider(entityMapper)
    private val _reviewsListSize = MutableStateFlow(0)
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()
    val reviewsListSize = _reviewsListSize.asStateFlow()
    val item = _item.asStateFlow()
    val eventFlow = eventChannel.receiveAsFlow()
    fun onViewCreated(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val reviews = reviewRepository.getReviews(id)
                val items = itemProvider.getItems(reviews)
                _reviewsListSize.value = reviews.size
                _item.value = items
                _isLoading.value = false
            } catch (ex: Exception) {
                _isLoading.value = false
                eventChannel.send(Event.ExceptionEvent)
            }
        }
    }
}
