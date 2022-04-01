package ru.itis.tinkoff.project.features.profile.data

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer

data class ProfileOptionListItem(
    override val profileOptions: List<ProfileOptionListRenderer.ProfileOptionItem>
) : ComparableItem, ProfileOptionListRenderer.RenderContract
