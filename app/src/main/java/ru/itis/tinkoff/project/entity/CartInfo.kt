package ru.itis.tinkoff.project.entity

data class CartInfo(
    val cartProducts: List<CartProduct>,
    val totalSum: Int
)
