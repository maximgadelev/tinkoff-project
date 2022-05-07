package ru.itis.tinkoff.project.features.productPage.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.itis.tinkoff.project.features.productPage.ui.fragments.CharacteristicsFragment
import ru.itis.tinkoff.project.features.productPage.ui.fragments.DescriptionFragment

class PagerAdapter(fragment: Fragment, val dataDesc: String, val dataCharacter: String) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            val fragment = DescriptionFragment()
            fragment.arguments = Bundle().apply {
                putString("description", dataDesc)
            }
            return fragment
        }
        return CharacteristicsFragment()
    }
}
