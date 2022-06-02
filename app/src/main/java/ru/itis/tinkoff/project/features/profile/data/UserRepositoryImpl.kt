package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.Profile

class UserRepositoryImpl(
    private val api: Api
) : UserRepository {
    override suspend fun getUser(): Profile = api.getProfileInfo()
}
