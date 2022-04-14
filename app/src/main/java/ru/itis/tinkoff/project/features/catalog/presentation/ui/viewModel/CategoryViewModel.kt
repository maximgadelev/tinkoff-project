package ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.features.catalog.data.CategoryRepository
import ru.itis.tinkoff.project.features.catalog.presentation.ui.CatalogFragment
import kotlin.properties.ReadOnlyProperty

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _item = MutableStateFlow<List<Category>>(emptyList())
    val item = _item.asStateFlow()

    init {
        viewModelScope.launch {
            val itemList = categoryRepository.getCategories()
            _item.value = itemList
        }
    }
}