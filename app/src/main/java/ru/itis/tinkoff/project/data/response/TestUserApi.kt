package ru.itis.tinkoff.project.data.response

import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.entity.User

class TestUserApi : Api {
    override suspend fun getCategories() {
        //
    }

    override suspend fun getProducts() {
        //
    }

    override suspend fun getProfileInfo(id: Long): User {
        return User(0, "Ivan", "Ivanov", "some_email@.com", "79467384573", "pass", null, emptyList())
    }

    override suspend fun getPromotions() {
        //
    }
}
