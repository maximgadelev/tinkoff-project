package ru.itis.tinkoff.project.features.catalog.ui

import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.features.catalog.utils.CategoryItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CatalogItemProvider(
    val entityMapper: EntityMapper
) {
    fun getItemList(categories: List<Category>): List<CategoryItem> {
        val resultList = mutableListOf<CategoryItem>()
        resultList += CategoryItem.CategoryListItem(
            entityMapper.mapCategoriesToCategoryItem(
                categories
            )
        )
        return resultList
    }
}
