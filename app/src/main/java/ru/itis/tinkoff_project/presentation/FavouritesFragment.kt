package ru.itis.tinkoff_project.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tinkoff_project.R
import com.example.tinkoff_project.databinding.FavouritesFragmentBinding

class FavouritesFragment : Fragment(R.layout.favourites_fragment) {
    private var binding: FavouritesFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}