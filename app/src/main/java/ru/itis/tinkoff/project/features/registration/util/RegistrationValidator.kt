package ru.itis.tinkoff.project.features.registration.util

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern.compile

@SuppressWarnings("MagicNumber")
class RegistrationValidator {
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 8
    }

    fun isValidName(name: String): Boolean {
        val namePattern = "^([А-Я][а-я]*)\$"
        return !TextUtils.isEmpty(name) && name.length >= 2 && compile(namePattern).matcher(name)
            .matches()
    }

    fun isValidSurname(surname: String): Boolean {
        val surnamePattern = "^([А-Я][а-я]*)\$"
        return !TextUtils.isEmpty(surname) && surname.length >= 2 && compile(surnamePattern).matcher(
            surname
        ).matches()
    }

    fun isValidNumber(number: String): Boolean {
        val numberPattern = "^([0-9]{11})\$"
        return !TextUtils.isEmpty(number) && number.length == 11 && compile(numberPattern).matcher(
            number
        ).matches()
    }

    fun isValidBirthDate(date: String): Boolean {
        val birthPattern = "^(([0-2][0-9]|[3][0-1])\\.([0][1-9]|[1][0-2])\\.[1-2][0-9][0-9][0-9])\$"
        return !TextUtils.isEmpty(date) && compile(birthPattern).matcher(date).matches()
    }
}
