package ru.itis.tinkoff.project.features.profile.ui.utils

import android.content.Context
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionListItem
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer

class OptionItemProvider {
    fun getItemList(context: Context?): ProfileOptionListItem {
        return ProfileOptionListItem(
            profileOptions = listOf(
                ProfileOptionListRenderer.ProfileOptionItem(
                    context?.resources?.getString(R.string.my_orders).toString(),
                    R.drawable.ic_outline_shopping_bag_24
                ),
                ProfileOptionListRenderer.ProfileOptionItem(
                    context?.resources?.getString(R.string.help).toString(),
                    R.drawable.ic_outline_help_outline_24
                ),
            )
        )
    }
}
