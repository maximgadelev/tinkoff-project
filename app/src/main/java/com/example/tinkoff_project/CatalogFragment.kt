package com.example.tinkoff_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tinkoff_project.databinding.CatalogFragmentBinding
import com.example.tinkoff_project.databinding.MenuFragmentBinding

class CatalogFragment : Fragment(R.layout.catalog_fragment) {
    private var binding: CatalogFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CatalogFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}