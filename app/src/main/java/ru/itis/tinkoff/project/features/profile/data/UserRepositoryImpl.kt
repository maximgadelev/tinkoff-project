package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.domain.repositories.UserRepository

class UserRepositoryImpl (
    private val api: Api
) : UserRepository
{
    override suspend fun getUser(id: Int) = api.getProfileInfo()
}
