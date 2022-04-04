package ru.itis.tinkoff.project.features.profile.ui.utils

import android.content.Context
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionItem

class OptionItemProvider {
    fun getItemList(context: Context?): List<ProfileOptionItem> {
        return listOf(
            ProfileOptionItem(
                context?.resources?.getString(R.string.my_orders).toString(),
                R.drawable.ic_outline_shopping_bag_24
            ),
            ProfileOptionItem(
                context?.resources?.getString(R.string.help).toString(),
                R.drawable.ic_outline_help_outline_24
            ),
        )
    }
}
