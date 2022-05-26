package ru.itis.tinkoff.project.features.authorization.ui

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
import ru.itis.tinkoff.project.databinding.AuthorizationFragmentBinding
import ru.itis.tinkoff.project.features.common.Event

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {
    private val viewModel: AuthorizationViewModel by viewModel()
    private val viewBinding by viewBinding(AuthorizationFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.TextViewValid.visibility = View.GONE
        viewBinding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.ButtonEnter.setOnClickListener {
            viewModel.loginUser(
                viewBinding.EditTextEmail.text.toString(),
                viewBinding.EditTextPassword.text.toString()
            )
            viewModel.eventFlow.onEach {
                when (it) {
                    Event.NavigateToMenuEvent ->
                        findNavController().navigate(R.id.action_authorizationFragment_to_menu)
                    Event.ExceptionEvent -> {
                        viewBinding.TextViewValid.visibility = View.VISIBLE
                        viewBinding.EditTextEmail.text?.clear()
                        viewBinding.EditTextPassword.text?.clear()
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }
}
