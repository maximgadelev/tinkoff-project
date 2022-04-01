package ru.itis.tinkoff.project.features.profile.ui.utils

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionListItem
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer

class OptionItemProvider {
    fun getItemList(): ProfileOptionListItem {
        return ProfileOptionListItem(
            profileOptions = listOf(
                ProfileOptionListRenderer.ProfileOptionItem(
                    "Мои заказы",
                    R.drawable.ic_outline_shopping_bag_24
                ),
                ProfileOptionListRenderer.ProfileOptionItem(
                    "Помощь",
                    R.drawable.ic_outline_help_outline_24
                ),
            )
        )
    }
}
