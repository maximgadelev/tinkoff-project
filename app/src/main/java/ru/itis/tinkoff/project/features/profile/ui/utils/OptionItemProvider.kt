package ru.itis.tinkoff.project.features.profile.ui.utils

import ru.itis.tinkoff.project.features.profile.data.ProfileOption
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionListItem

class OptionItemProvider(
    private val profileEntityMapper: ProfileEntityMapper
) {
    fun getItemList(options: List<ProfileOption>) : List<ProfileOptionListItem>{
        val resultList = mutableListOf<ProfileOptionListItem>()
        resultList += ProfileOptionListItem(profileEntityMapper.mapOptionToOptionList(options))
        return resultList
    }
}
