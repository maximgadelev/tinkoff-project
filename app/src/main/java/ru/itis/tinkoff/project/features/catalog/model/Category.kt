package ru.itis.tinkoff.project.features.catalog.model

data class Category (
    val id: Int,
    val photo: Int,
    val name: String,
) : DisplayableItem

