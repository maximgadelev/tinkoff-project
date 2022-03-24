package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.ProfileOption

object ProfileOptionsRepository {

    val options = arrayListOf(
        ProfileOption(1, R.drawable.ic_outline_shopping_bag_24, "Мои заказы"),
        ProfileOption(2, R.drawable.ic_outline_help_outline_24, "Помощь"),
    )
}
