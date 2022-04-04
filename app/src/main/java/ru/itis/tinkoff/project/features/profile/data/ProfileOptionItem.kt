package ru.itis.tinkoff.project.features.profile.data

import ru.haroncode.aquarius.core.diffutil.ComparableItem
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer

data class ProfileOptionItem (
    override val title: String,
    override val icon: Int
) : ComparableItem, ProfileOptionListRenderer.RenderContract
