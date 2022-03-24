package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User

object TestUserRepository  : UserRepository {
    private val users = arrayListOf(
        User(0, "Иван", "Иванов", "ivan_ivanov@gmail.com",
            "+79625395639", "thisIsMyPassword", R.drawable.ic_shopping_cart_24),
        User(1, "Петр", "Петров", "iampiter@gmail.com",
        "+79649680024", "andThisIsMyPassword", R.drawable.user_photo_default)
    )

    override suspend fun getUser(id: Int): User {
        return users[id]
    }
}
