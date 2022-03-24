package ru.itis.tinkoff.project.entity

import android.graphics.Bitmap

data class User (
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val avatarPhoto: Bitmap,
)
