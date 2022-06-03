package ru.itis.tinkoff.project.features.confirm.ui

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
import ru.itis.tinkoff.project.databinding.ConfirmFragmentBinding
import ru.itis.tinkoff.project.features.common.Event

class ConfirmFragment : Fragment(R.layout.confirm_fragment) {
    private val viewModel: ConfirmViewModel by viewModel()
    private val viewBinding by viewBinding(ConfirmFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.ButtonConfirm.setOnClickListener {
            viewModel.confirmUser(viewBinding.EditTextCode.text.toString())
        }
        viewModel.eventFlow.onEach {
            when (it) {
                Event.NavigateToAuthorizationEvent ->
                    findNavController().navigate(R.id.action_confirmFragment_to_authorizationFragment)
                Event.ExceptionEvent -> {
                    viewBinding.TextViewValid.visibility = View.VISIBLE
                    viewBinding.EditTextCode.text?.clear()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
