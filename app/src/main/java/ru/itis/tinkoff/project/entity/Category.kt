package ru.itis.tinkoff.project.entity

import ru.haroncode.aquarius.core.diffutil.ComparableItem

data class Category (
    val id: Long,
    val photo: Int,
    val name: String,
) : ComparableItem



