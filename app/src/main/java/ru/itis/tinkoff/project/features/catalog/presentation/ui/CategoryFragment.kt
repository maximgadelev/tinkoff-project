package ru.itis.tinkoff.project.features.catalog.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CatalogFragmentBinding

class CategoryFragment : Fragment(R.layout.category_fragment) {
    private val viewBinding by viewBinding(CatalogFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

    }
}