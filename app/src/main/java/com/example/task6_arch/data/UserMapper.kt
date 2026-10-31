package com.example.task6_arch.data

import com.example.task6_arch.domain.User

fun UserDto.toDomain(): User {
    return User(
        id = this.userId ?: "unknown_id",
        // Соединяем имя и фамилию для domain-модели
        name = "${this.firstName ?: ""} ${this.lastName ?: ""}".trim().ifEmpty { "Anonymous" },
        email = this.userEmail ?: "no-email@example.com"
    )
}