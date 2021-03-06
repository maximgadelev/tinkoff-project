package ru.itis.tinkoff.project.entity

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val image: List<String>,
    val characteristic: List<Characteristic>,
    val price: BigDecimal,
    val description: String,
    val companyName: String,
    val rating: Double
)
