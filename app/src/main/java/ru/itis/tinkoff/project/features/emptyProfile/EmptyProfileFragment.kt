package ru.itis.tinkoff.project.features.emptyProfile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.EmptyProfileFragmentBinding

class EmptyProfileFragment : Fragment(R.layout.empty_profile_fragment) {
    private val viewBinding by viewBinding(EmptyProfileFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ButtonToRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_emptyProfileFragment_to_registrationFragment)
        }
        viewBinding.ButtonToAuthorization.setOnClickListener {
            findNavController().navigate(R.id.action_emptyProfileFragment_to_authorizationFragment)
        }
    }
}
