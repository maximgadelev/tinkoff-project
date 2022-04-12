package ru.itis.tinkoff.project.entity

import java.math.BigDecimal

data class Product(
    val name: String,
    val image: String,
    val price: BigDecimal,
    val companyName: String,
)
