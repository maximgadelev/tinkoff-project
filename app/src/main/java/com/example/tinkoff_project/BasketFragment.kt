package com.example.tinkoff_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tinkoff_project.databinding.BasketFragmentBinding

class BasketFragment : Fragment(R.layout.basket_fragment) {
    private var binding: BasketFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}