package com.example.ciguide.Data.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://127.0.0.1:8000/"

    // Creamos un interceptor para loguear las respuestas
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // BODY muestra toda la respuesta
    }

    // Creamos un cliente de OkHttp y le añadimos el interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val api: ApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Usamos el cliente con el interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}
