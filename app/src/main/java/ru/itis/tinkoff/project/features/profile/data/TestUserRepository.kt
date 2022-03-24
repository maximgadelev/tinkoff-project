package ru.itis.tinkoff.project.features.profile.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.entity.User

object TestUserRepository  : UserRepository {
    val users = arrayOf<User>(
        User(1, "Иван", "Иванов", "ivan_ivanov@gmail.com", "+79625395639", "thisIsMyPassword", R.drawable.ic_shopping_cart_24)
    )

    override suspend fun getUser(id: Int): User {
        return users[0]
    }
}
