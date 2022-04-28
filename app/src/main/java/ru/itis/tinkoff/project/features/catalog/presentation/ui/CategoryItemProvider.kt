package ru.itis.tinkoff.project.features.catalog.presentation.ui

import ru.itis.tinkoff.project.entity.Category
import ru.itis.tinkoff.project.features.catalog.utils.CatalogListItem
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper

class CategoryItemProvider (
    private val entityMapper: EntityMapper
) {
   fun getItemList(categories: List<Category>): List<CatalogListItem> {
       val resultList  = mutableListOf<CatalogListItem>()
       resultList += CatalogListItem.CategoryListItem(
           entityMapper.mapCategoryToCatalogListItem(categories)
       )
       return resultList
   }
}