package com.example.architecture_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Модель элемента приложения
data class AppItem(
    val id: Int,
    val title: String,
    val subtitle: String
)

// Тестовые данные витрины
val showcaseApps = listOf(
    AppItem(1, "Atom Idle", "A"),
    AppItem(2, "Roblox", "R"),
    AppItem(3, "Crazy Taxi", "C")
)

class MainViewModel : ViewModel() {

    // Состояние списка приложений
    private val _apps = MutableStateFlow<List<AppItem>>(showcaseApps)
    val apps: StateFlow<List<AppItem>> = _apps.asStateFlow()

    // Канал для одноразовых событий (Snackbar), чтобы не показывать его повторно при повороте
    private val _snackbarEvents = MutableSharedFlow<String>()
    val snackbarEvents: SharedFlow<String> = _snackbarEvents.asSharedFlow()

    // Клик по Toolbar — триггерим показ Snackbar
    fun onToolbarClicked() {
        viewModelScope.launch {
            _snackbarEvents.emit("Привет из Toolbar! Клик обработан во ViewModel.")
        }
    }
}