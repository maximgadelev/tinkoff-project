package ru.itis.tinkoff.project.features.registration.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.RegistrationFragmentBinding
import ru.itis.tinkoff.project.features.common.Event

class RegistrationFragment : Fragment(R.layout.registration_fragment) {
    private val viewBinding by viewBinding(RegistrationFragmentBinding::bind)
    private val viewModel: RegistrationFragmentViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ButtonRegistration.setOnClickListener {
            viewModel.registerUser(
                viewBinding.EditTextName.text.toString(),
                viewBinding.EditTextSurname.text.toString(),
                viewBinding.EditTextRegistrationEmail.text.toString(),
                viewBinding.EditTextPhoneNumber.text.toString(),
                viewBinding.EditTextRegistrationPassword.text.toString()
            )
            viewModel.eventFlow.onEach {
                when (it) {
                    Event.NavigateToAuthorizationEvent ->
                        findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
                    Event.ExceptionEvent ->
                        viewBinding.TextViewValid.visibility = View.VISIBLE
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}
