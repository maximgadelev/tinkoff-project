package ru.itis.tinkoff.project.features.registration.data

import ru.itis.tinkoff.project.data.api.TokenApi
import ru.itis.tinkoff.project.data.request.RegistrationRequest

class RegistrationRepository(
    private val tokenApi: TokenApi
) {
    suspend fun registerUser(
        firstName: String,
        secondName: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        tokenApi.registerUser(
            RegistrationRequest(
                firstName,
                secondName,
                email,
                phoneNumber,
                password
            )
        )
    }
}