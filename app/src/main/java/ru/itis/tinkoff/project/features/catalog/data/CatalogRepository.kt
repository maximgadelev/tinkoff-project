package ru.itis.tinkoff.project.features.catalog.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.data.response.CategoryResponse
import ru.itis.tinkoff.project.entity.Category

class CatalogRepository(
    private val api: Api,
    private val mapper: ResponseMapper
) {
    suspend fun getCategories(): List<Category> {
        return mapper.mapCategoriesResponseListToCategoryList(api.getCategories())
    }
}
