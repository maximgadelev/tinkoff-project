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
import ru.itis.tinkoff.project.features.profile.data.ProfileOptionsRepository
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository
import ru.itis.tinkoff.project.features.profile.ui.utils.OptionItemProvider
import ru.itis.tinkoff.project.features.profile.ui.utils.ProfileEntityMapper

class UserViewModel(
    private val userRepository: UserRepository,
    private val userIdRepository: UserIdRepository,
    private val profileOptionsRepository: ProfileOptionsRepository,
    profileEntityMapper: ProfileEntityMapper
) : ViewModel() {
    private var _user = MutableStateFlow<User>(initializeEmptyUser())
    val user: StateFlow<User> = _user.asStateFlow()

    private var _option = MutableStateFlow<List<ProfileOptionListItem>>(emptyList())
    val option: StateFlow<List<ProfileOptionListItem>> = _option.asStateFlow()

    private val itemProvider = OptionItemProvider(profileEntityMapper)

    init {
        viewModelScope.launch {
            val user = userRepository.getUser(userIdRepository.getUserId())
            _user.value = user
            val options = profileOptionsRepository.getOptions()
            val items = itemProvider.getItemList(options)
            _option.value = items
        }
    }

    private fun initializeEmptyUser() = User(0, "", "", "", "", "", null, emptyList())
}
