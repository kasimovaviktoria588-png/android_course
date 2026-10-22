package com.example.vk_task

import kotlinx.serialization.Serializable

// Модель приложения
@Serializable
data class AppItem(
    val id: Int,
    val title: String,
    val category: String
)

// Захардкоженные 9 приложений (по 3 на каждую из 3 страниц)
val showcaseApps = listOf(
    AppItem(1, "Atom Idle", "Кликкер"),
    AppItem(2, "Roblox", "Песочница"),
    AppItem(3, "Crazy Taxi", "Гонки"),
    AppItem(4, "Subway Surfers", "Аркада"),
    AppItem(5, "Minecraft", "Выживание"),
    AppItem(6, "Brawl Stars", "Экшен"),
    AppItem(7, "Among Us", "Стратегия"),
    AppItem(8, "Standoff 2", "Шутер"),
    AppItem(9, "Toca Life", "Симулятор")
)

// Type-safe маршруты для навигации
@Serializable
data object MainListDestination

@Serializable
data class AppDetailsDestination(val appId: Int)