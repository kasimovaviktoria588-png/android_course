package com.example.task6_arch.data

import com.example.task6_arch.domain.User
import com.example.task6_arch.domain.UserRepository
class UserRepositoryImpl: UserRepository {

    override fun getUserProfile(): User {
        val rawFakeData = UserDto(
            userId = "42-abc",
            firstName = "Иван",
            lastName = "Иванов",
            userEmail = "ivan.dev@android.ru"
        )

        return rawFakeData.toDomain()
    }
}