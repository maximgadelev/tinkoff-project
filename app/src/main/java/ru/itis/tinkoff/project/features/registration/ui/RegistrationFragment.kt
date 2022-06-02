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
import ru.itis.tinkoff.project.features.registration.util.RegistrationValidator

@SuppressWarnings("ComplexCondition")
class RegistrationFragment : Fragment(R.layout.registration_fragment) {
    private val viewBinding by viewBinding(RegistrationFragmentBinding::bind)
    private val viewModel: RegistrationFragmentViewModel by viewModel()
    val registrationValidator = RegistrationValidator()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.ButtonRegistration.setOnClickListener {
            validateFields()
            if (validateFields()) {
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

    private fun validateFields(): Boolean {
        val name = viewBinding.EditTextName.text.toString()
        val surname = viewBinding.EditTextSurname.text.toString()
        val email = viewBinding.EditTextRegistrationEmail.text.toString()
        val number = viewBinding.EditTextPhoneNumber.text.toString()
        val password = viewBinding.EditTextRegistrationPassword.text.toString()

        if (!registrationValidator.isValidEmail(email) || !registrationValidator.isValidPassword(
                password
            ) || !registrationValidator.isValidNumber(number) || !registrationValidator.isValidName(
                name
            ) || !registrationValidator.isValidSurname(surname)
        ) {
            if (!registrationValidator.isValidPassword(password)) {
                viewBinding.EditTextRegistrationPassword.error =
                    getString(R.string.no_valid_password)
            }
            if (!registrationValidator.isValidEmail(email)) {
                viewBinding.EditTextRegistrationEmail.error =
                    getString(R.string.no_valid_email)
            }
            if (!registrationValidator.isValidNumber(number)) {
                viewBinding.EditTextPhoneNumber.error =
                    getString(R.string.no_valid_number)
            }
            if (!registrationValidator.isValidName(name)) {
                viewBinding.EditTextName.error =
                    getString(R.string.no_valid_name)
            }
            if (!registrationValidator.isValidSurname(surname)) {
                viewBinding.EditTextSurname.error =
                    getString(R.string.no_valid_surname)
            }
            return false
        }
        return true
    }
}
