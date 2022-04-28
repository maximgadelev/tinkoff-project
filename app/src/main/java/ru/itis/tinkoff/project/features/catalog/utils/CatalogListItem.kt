package ru.itis.tinkoff.project.features.catalog.utils

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.catalog.presentation.ui.renderer.CatalogRenderer

sealed class CatalogListItem : ComparableItem{
    data class CategoryListItem(
        override val categories: List<CatalogRenderer.Category>
    ) : CatalogListItem(), CatalogRenderer.RenderContract
}
