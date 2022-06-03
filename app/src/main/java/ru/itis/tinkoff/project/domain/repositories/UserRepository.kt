package ru.itis.tinkoff.project.domain.repositories

import ru.itis.tinkoff.project.entity.Profile

interface UserRepository {
    suspend fun getUser(): Profile
}
