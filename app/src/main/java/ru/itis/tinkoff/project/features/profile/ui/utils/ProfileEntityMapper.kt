package ru.itis.tinkoff.project.features.profile.ui.utils

import ru.itis.tinkoff.project.features.profile.data.ProfileOption
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer

class ProfileEntityMapper {
    fun mapOptionToOptionList(options: List<ProfileOption>): List<ProfileOptionListRenderer.ProfileOption> {
        return options.map { option ->
            ProfileOptionListRenderer.ProfileOption(
                option.title,
                option.icon
            )
        }
    }
}
