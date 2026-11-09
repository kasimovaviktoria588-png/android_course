package com.example.networkcatalog.model

import com.google.gson.annotations.SerializedName

data class AppItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("icon")
    val iconUrl: String? = null
)