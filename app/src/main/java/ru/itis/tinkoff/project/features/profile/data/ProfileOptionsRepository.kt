package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.ProfileOption

class ProfileOptionsRepository {

    val options = arrayListOf(
        ProfileOption("Мои заказы", R.drawable.ic_outline_shopping_bag_24),
        ProfileOption("Помощь", R.drawable.ic_outline_help_outline_24),
    )

    fun getOptions(): List<ProfileOption> = options
}
