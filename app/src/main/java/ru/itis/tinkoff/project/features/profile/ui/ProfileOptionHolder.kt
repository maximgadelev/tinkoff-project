package ru.itis.tinkoff.project.features.profile.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.itis.tinkoff.project.databinding.ItemOptionBinding
import ru.itis.tinkoff.project.features.profile.data.ProfileOption

class ProfileOptionHolder (
    item: View
) : RecyclerView.ViewHolder(item) {

    private var profileOption: ProfileOption? = null
    private val binding = ItemOptionBinding.bind(item)

    fun bind(item: ProfileOption) {
        this.profileOption = item
        with(binding) {
            tvOptionTitle.text = profileOption?.title
            profileOption?.icon?.let {
                ivIconOption.setImageResource(it) }
            ibIconNext.setOnClickListener{

            }
        }
    }
}
