package ru.itis.tinkoff.project.entity

data class Review(
    val id: Int,
    val advantages: String,
    val comment: String,
    val date: String,
    val disadvantages: String,
    val experience: String,
    val rating: Double,
    val profileImage:String,
    val profileName:String
)
