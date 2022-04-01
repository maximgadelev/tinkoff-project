package ru.itis.tinkoff.project.features.profile.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionListItem
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository
import ru.itis.tinkoff.project.features.profile.ui.utils.OptionItemProvider

class UserViewModel(
    private val userRepository: UserRepository,
    private val userIdRepository: UserIdRepository,
) : ViewModel() {
    private var _user = MutableStateFlow<User>(initializeEmptyUser())
    val user: StateFlow<User> = _user.asStateFlow()

    private var _option = MutableStateFlow<ProfileOptionListItem>(initializeEmptyItemList())
    val option: StateFlow<ProfileOptionListItem> = _option.asStateFlow()

    private val itemProvider = OptionItemProvider()

    init {
        viewModelScope.launch {
            val user = userRepository.getUser(userIdRepository.getUserId())
            _user.value = user
            val items = itemProvider.getItemList()
            _option.value = items
        }
    }

    private fun initializeEmptyUser() = User(0, "", "", "", "", "", null)

    private fun initializeEmptyItemList() = ProfileOptionListItem(profileOptions = emptyList())
}
