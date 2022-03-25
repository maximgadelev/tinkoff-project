package ru.itis.tinkoff.project.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User

class GetUserUseCase(
    private val repository: UserRepository,
    private val scope: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(id:Long): User =
        withContext(scope) {
            repository.getUser(id)
        }
}
