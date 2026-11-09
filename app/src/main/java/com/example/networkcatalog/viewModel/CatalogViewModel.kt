package com.example.networkcatalog.viewModel

import androidx.compose.runtime.getValue
import com.example.networkcatalog.model.AppItem
import com.example.networkcatalog.model.RetrofitClient
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    // Состояние списка приложений для интерфейса
    var appList by mutableStateOf<List<AppItem>>(emptyList())
        private set

    // Состояние ошибки (если сервер недоступен)
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Состояние загрузки
    var isLoading by mutableStateOf(false)
        private set

    init {
        loadCatalog()
    }

    fun loadCatalog() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // Вызываем наш GET-запрос из ApiService
                appList = RetrofitClient.apiService.getCatalog()
            } catch (e: Exception) {
                errorMessage = "Ошибка загрузки: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}