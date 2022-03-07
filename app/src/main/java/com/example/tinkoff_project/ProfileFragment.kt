package com.example.tinkoff_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tinkoff_project.databinding.FavouritesFragmentBinding
import com.example.tinkoff_project.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {
    private var binding: ProfileFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}