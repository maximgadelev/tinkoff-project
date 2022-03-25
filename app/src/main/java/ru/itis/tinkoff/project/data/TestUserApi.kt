package ru.itis.tinkoff.project.data

import ru.itis.tinkoff.project.R
import ru.itis.tinkoff.project.entity.User

interface TestUserApi {

    suspend fun getProfileInfo(id: Long) : User{
        return User(0, "Иван", "Иванов", "ivan_ivanov@gmail.com",
                "+79625395639", "thisIsMyPassword", R.drawable.ic_shopping_cart_24, listOf())
    }
}
