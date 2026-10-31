package com.example.task6_arch.presentation

import androidx.lifecycle.ViewModel
import com.example.task6_arch.data.UserRepositoryImpl
import com.example.task6_arch.domain.User
import com.example.task6_arch.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {

    private val repository: UserRepository = UserRepositoryImpl()

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val user = repository.getUserProfile()
        // Обновляем состояние для экрана
        _userState.value = user
    }
}