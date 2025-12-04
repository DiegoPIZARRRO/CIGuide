package com.example.ciguide.Data.Network

import com.example.ciguide.Data.Model.Boss
import com.example.ciguide.Data.Model.Item
import retrofit2.http.GET

interface ApiServices {
    @GET("bosses")
    suspend fun getBosses(): List<Boss>

    @GET("items")
    suspend fun getItems(): List<Item>
}