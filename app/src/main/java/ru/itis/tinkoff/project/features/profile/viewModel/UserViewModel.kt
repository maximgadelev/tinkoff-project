package ru.itis.tinkoff.project.features.profile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.tinkoff.project.domain.usecases.GetUserUseCase
import ru.itis.tinkoff.project.entity.User

class UserViewModel(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private var __user: MutableLiveData<Result<User>> = MutableLiveData()
    val user: LiveData<Result<User>> = __user

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    suspend fun getUser(id: Long) {
        viewModelScope.launch {
            try {
                val user = getUserUseCase(id)
                __user.value = Result.success(user)
            } catch (ex: Exception) {
                __user.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }
}
