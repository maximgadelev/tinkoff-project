package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.data.TestUserApi
import ru.itis.tinkoff.project.domain.repositories.UserRepository

class UserRepositoryImpl (
    private val api: TestUserApi
) : UserRepository
{
    override suspend fun getUser(id: Long) = api.getProfileInfo(id)
}
