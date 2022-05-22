package ru.itis.tinkoff.project.features.authorization.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {
    private val viewBinding by viewBinding(AuthorizationFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navigation?.visibility = View.GONE
        viewBinding.ButtonEnter.setOnClickListener {
            viewBinding.TextViewValid.visibility = View.VISIBLE
        }
    }
}
