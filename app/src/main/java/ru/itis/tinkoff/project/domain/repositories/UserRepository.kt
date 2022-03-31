package ru.itis.tinkoff.project.domain.repositories

import ru.itis.tinkoff.project.entity.User

interface UserRepository {
    suspend fun getUser(id: Long): User
}
