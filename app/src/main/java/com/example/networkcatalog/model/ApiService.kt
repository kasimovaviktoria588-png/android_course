package com.example.networkcatalog.model

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // 1. Запрос каталога приложений
    @GET("catalog")
    suspend fun getCatalog(): List<AppItem>

    // 2. Запрос информации о конкретном приложении по id
    @GET("{id}")
    suspend fun getAppDetails(
        @Path("id") appId: Int
    ): AppItem
}