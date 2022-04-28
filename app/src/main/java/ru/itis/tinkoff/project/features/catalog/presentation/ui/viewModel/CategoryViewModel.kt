package ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.features.catalog.data.CategoryRepository
import ru.itis.tinkoff.project.features.catalog.presentation.ui.CategoryItemProvider
import ru.itis.tinkoff.project.features.catalog.utils.CatalogListItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CategoryViewModel(
    private val categoryRepository: CategoryRepository,
    private val entityMapper: EntityMapper
) : ViewModel() {
    private val _item = MutableStateFlow<List<CatalogListItem>>(emptyList())
    val item = _item.asStateFlow()
    private val itemProvider = CategoryItemProvider(entityMapper)

    init {
        viewModelScope.launch {
            viewModelScope.launch {
                val categories = categoryRepository.getCategories()
                val items = itemProvider.getItemList(categories)
                _item.value = items
            }
        }
    }
}