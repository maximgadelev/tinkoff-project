package ru.itis.tinkoff.project.features.productPage.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.CharacteristicsFragmentBinding

class CharacteristicsFragment : Fragment(R.layout.characteristics_fragment) {
    private val viewBinding by viewBinding(CharacteristicsFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
