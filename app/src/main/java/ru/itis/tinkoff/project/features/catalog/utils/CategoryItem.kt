package ru.itis.tinkoff.project.features.catalog.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.catalog.ui.renderer.CategoryListRenderer

sealed class CategoryItem : ComparableItem {
    data class CategoryListItem(
        override val categories: List<CategoryListRenderer.Category>
    ) : CategoryItem(), CategoryListRenderer.RenderContract
}
