package ru.itis.tinkoff.project.features.catalog.repository

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.catalog.model.Category
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem

object CategoryRepository {
    val categories = arrayListOf<DisplayableItem>(
        Category(0, R.drawable.ic_baseline_navigate_next_24, "Электроника"),
        Category(1, R.drawable.common_google_signin_btn_icon_dark_focused, "Бытовая техника"),
        Category(2, R.drawable.ic_baseline_navigate_next_24, "Одежда"),
        Category(3, R.drawable.ic_baseline_navigate_next_24, "Аксессуары"),
        Category(4, R.drawable.ic_baseline_navigate_next_24, "Обувь"),
        Category(5, R.drawable.ic_baseline_navigate_next_24, "Красота"),
        Category(6, R.drawable.ic_baseline_navigate_next_24, "Здоровье"),
        Category(7, R.drawable.ic_baseline_navigate_next_24, "Товары для дома"),
        Category(8, R.drawable.ic_baseline_navigate_next_24, "Строительство и ремонт"),
        Category(9, R.drawable.ic_baseline_navigate_next_24, "Автотовары"),
        Category(10, R.drawable.ic_baseline_navigate_next_24, "Детские товары"),
        Category(11, R.drawable.ic_baseline_navigate_next_24, "Хобби и творчество"),
        Category(12, R.drawable.ic_baseline_navigate_next_24, "Товары для взрослых"),
        Category(13, R.drawable.ic_baseline_navigate_next_24, "Спорт и отдых"),
        Category(14, R.drawable.ic_baseline_navigate_next_24, "Продукты питания"),
        Category(15, R.drawable.ic_baseline_navigate_next_24, "Бытовая химия и личная гигиена"),
        Category(12, R.drawable.ic_baseline_navigate_next_24, "Канцтовары"),
        Category(13, R.drawable.ic_baseline_navigate_next_24, "Зоотовары"),
        Category(14, R.drawable.ic_baseline_navigate_next_24, "Книги"),
        Category(15, R.drawable.ic_baseline_navigate_next_24, "Дача, сад и огород")
    )

}