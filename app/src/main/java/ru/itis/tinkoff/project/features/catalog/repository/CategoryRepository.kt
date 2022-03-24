package ru.itis.tinkoff.project.features.catalog.repository

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.catalog.model.Category
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem

object CategoryRepository {
    val categories = arrayListOf<DisplayableItem>(
        Category(1, R.drawable.ic_baseline_navigate_next_24, "1"),
        Category(2, R.drawable.ic_baseline_navigate_next_24, "2"),
        Category(3, R.drawable.ic_baseline_navigate_next_24, "3"),
        Category(4, R.drawable.ic_baseline_navigate_next_24, "4"),
        Category(5, R.drawable.ic_baseline_navigate_next_24, "5")
    )

}