package ru.itis.tinkoff.project.features.profile.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.ProfileOption

class ProfileOptionsAdapter(
    private val list: List<ProfileOption>,
) : RecyclerView.Adapter<ProfileOptionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)
        return ProfileOptionHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileOptionHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
