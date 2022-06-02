package ru.itis.tinkoff.project.features.common.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.haroncode.aquarius.core.clicker.Clicker

class CustomClicker<ItemModel, VH : RecyclerView.ViewHolder>(
    private val consumer: (ItemModel, View) -> Unit,
) : Clicker<ItemModel, VH> {

    override fun onBindClicker(holder: VH) = Unit

    override fun invoke(holder: VH, view: View, item: ItemModel) = consumer(item, view)

    override fun onUnbindClicker(holder: VH) = Unit
}
