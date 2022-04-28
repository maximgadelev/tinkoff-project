package ru.itis.tinkoff.project.features.catalog.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.entity.Category

class CategoryRepository(
    private val api: Api
) {
    suspend fun getCategories(): List<Category> {
        return api.getCategories()
    }
}