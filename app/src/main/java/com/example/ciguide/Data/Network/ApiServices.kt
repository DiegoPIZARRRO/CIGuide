package com.example.ciguide.Data.Network

import com.example.ciguide.Data.Model.Boss
import com.example.ciguide.Data.Model.Item
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @GET("bosses")
    suspend fun getBosses(): List<Boss>

    @GET("items")
    suspend fun getItems(): List<Item>

    @POST("bosses/add")
    suspend fun addBoss(@Body boss: Boss): ResponseBody

    @DELETE("bosses/delete/{id}")
    suspend fun deleteBoss(@Path("id") id: Int): ResponseBody
}