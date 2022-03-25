package ru.itis.tinkoff.project.features.profile.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding
import ru.itis.tinkoff.project.di.DIContainer
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.ProfileViewModelFactory
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionsRepository
import ru.itis.tinkoff.project.features.profile.viewModel.UserViewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private lateinit var viewModel: UserViewModel
    private var userId: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefreshLayout()
        initFactory()
        initObservers()
        initRv()
        initUser(userId)
    }

    private fun initUser(id: Long) {
        lifecycleScope.launch {
            viewModel.getUser(id)
        }
    }

    private fun initSwipeToRefreshLayout() {
        with(viewBinding) {
            swipeRefreshLayout.setOnRefreshListener {
                initUser(userId)
                initObservers()
                swipeRefreshLayout.isRefreshing = false
            }
            swipeRefreshLayout.setColorSchemeResources(
                R.color.deep_orange
            )
        }
    }

    private fun initRv() {
        with(viewBinding) {
            rvOptions.adapter = ProfileOptionsAdapter(ProfileOptionsRepository.options)
        }
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            it.fold(
                onSuccess =
                { initUserInfo(it) },
                onFailure = { Toast.makeText(context, R.string.cant_load_data, Toast.LENGTH_SHORT).show() }
            )
        }
    }

    private fun initUserInfo(user: User) {
        with(viewBinding) {
            tvName.text = user.name
            tvSurname.text = user.surname
            tvOptionTitle.text // add active orders initialization later
        }
    }

    private fun initFactory() {
        val factory = ProfileViewModelFactory(DIContainer)
        viewModel = ViewModelProvider(
            this,
            factory
        )[UserViewModel::class.java]
    }
}
