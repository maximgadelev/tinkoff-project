package ru.itis.tinkoff.project.features.catalog.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.itis.tinkoff.project.features.catalog.model.DisplayableItem

class MainAdapter : ListDelegationAdapter<List<DisplayableItem>>(
    CategoryAdapterDelegate()
)