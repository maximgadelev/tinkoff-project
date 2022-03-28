package ru.itis.tinkoff.project.features.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment(R.layout.favourites_fragment) {

    private val viewBinding by viewBinding(FavouritesFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding
    }
}
