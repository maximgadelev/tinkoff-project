package ru.itis.tinkoff.project.entity

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.catalog.utils.DisplayableItem

data class Category (
    val id: Long,
    val photo: Int,
    val name: String,
) : DisplayableItem, ComparableItem



