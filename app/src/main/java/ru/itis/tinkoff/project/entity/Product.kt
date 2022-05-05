package ru.itis.tinkoff.project.entity

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val image: List<String>,
    val price: BigDecimal,
    val companyName: String,
)
