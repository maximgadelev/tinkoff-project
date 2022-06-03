package ru.itis.tinkoff.project.features.profile.ui.utils

import ru.itis.tinkoff.project.R

class OptionItemProvider {
    fun getItemList(): List<ProfileOptionItem> {
        return listOf(
            ProfileOptionItem(
                R.string.my_orders,
                R.drawable.ic_outline_shopping_bag_24
            ),
            ProfileOptionItem(
                R.string.help,
                R.drawable.ic_outline_help_outline_24
            ),
        )
    }
}
