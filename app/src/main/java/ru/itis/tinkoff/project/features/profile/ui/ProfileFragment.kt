package ru.itis.tinkoff.project.features.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionListItem
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer
import ru.itis.tinkoff.project.features.profile.ui.viewModel.UserViewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private val viewModel: UserViewModel by viewModel()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ProfileOptionListItem>()
            .renderer(ProfileOptionListItem::class, ProfileOptionListRenderer())
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initUser()
    }

    private fun initUser() {
        viewModel.user
            .onEach {
                fillUserInfo(it)
            }.launchIn(lifecycleScope)
    }

    private fun initRv() {
        with(viewBinding) {
            rvOptions.adapter = itemAdapter
        }
        viewModel.option.onEach {
            itemAdapter.differ.submitList(listOf(it))
        }.launchIn(lifecycleScope)
    }

    private fun fillUserInfo(user: User) {
        with(viewBinding) {
            tvName.text = user.name
            tvSurname.text = user.surname
            tvOptionTitle.text // add active orders initialization later
        }
    }
}
