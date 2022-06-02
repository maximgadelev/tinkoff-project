package ru.itis.tinkoff.project.entity

data class Promotion(
    val id: Int,
    val name: String,
    val isActive: Boolean,
    val image: String,
    val products: List<Product>
)
