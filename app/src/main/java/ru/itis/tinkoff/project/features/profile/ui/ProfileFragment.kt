package ru.itis.tinkoff.project.features.profile.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.haroncode.aquarius.core.RenderAdapterBuilder
import ru.haroncode.aquarius.core.base.strategies.DifferStrategies
import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.databinding.ProfileFragmentBinding
import ru.itis.tinkoff.project.entity.Profile
import ru.itis.tinkoff.project.features.common.Event
import ru.itis.tinkoff.project.features.profile.ui.renderer.ProfileOptionListRenderer
import ru.itis.tinkoff.project.features.profile.ui.utils.OptionItemProvider
import ru.itis.tinkoff.project.features.profile.ui.utils.ProfileOptionItem

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
    private val viewModel: UserViewModel by viewModel()
    private val optionItemProvider = OptionItemProvider()
    private val itemAdapter by lazy {
        RenderAdapterBuilder<ProfileOptionItem>()
            .renderer(ProfileOptionItem::class, ProfileOptionListRenderer())
            .build(DifferStrategies.withDiffUtilComparable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initUser()
        initSwipeToRefreshLayout()
        viewBinding.outBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_authorizationFragment)
        }
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
        itemAdapter.differ.submitList(optionItemProvider.getItemList())
    }

    private fun fillUserInfo(profile: Profile) {
        with(viewBinding) {
            tvName.text = profile.firstName
            tvSurname.text = profile.lastName
            /*val avatarBitmap = BitmapFactory.decodeFile(profile.profileImg)
            avatarBitmap?.also{
                ivAvatar.setImageBitmap(it)
            }*/
        }
    }

    private fun initSwipeToRefreshLayout() {
        with(viewBinding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.onViewCreated()
                swipeRefreshLayout.isRefreshing = false
            }
            swipeRefreshLayout.setColorSchemeResources(
                R.color.deep_orange
            )
        }
    }
}
