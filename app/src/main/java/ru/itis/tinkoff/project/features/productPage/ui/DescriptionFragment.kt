package ru.itis.tinkoff.project.features.productPage.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.DescriptionFragmentBinding

class DescriptionFragment : Fragment(R.layout.description_fragment) {
    private val viewBinding by viewBinding(DescriptionFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
