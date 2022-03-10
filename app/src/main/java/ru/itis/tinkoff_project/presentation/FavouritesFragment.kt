package ru.itis.tinkoff_project.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tinkoff_project.R
import com.example.tinkoff_project.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment(R.layout.favourites_fragment) {
    private val viewBinding: FavouritesFragmentBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return viewBinding.root
    }
}