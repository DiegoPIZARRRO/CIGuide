package com.example.ciguide.Data.Repository

import android.util.Log
import com.example.ciguide.Data.Model.Boss
import com.example.ciguide.Data.Network.RetrofitClient

//providence, DDD, yharon, exo mecanicos, calamitas

class WikiRepository {
    suspend fun getBosses(): List<Boss> {
        return try {
            val respuesta = RetrofitClient.api.getBosses()

            Log.d("API_BOSS","Se recibieron ${respuesta.size} jefes")
            respuesta
        } catch (e: Exception){
            Log.e("API_BOSS", "Error al cargar jefes: ${e.message}")
            emptyList()
        }
    }

    suspend fun addBoss(name: String, description: String, vida: String, imageUrl: String): Boolean{
        return try {
            val newBoss = Boss(
                id = 0,
                name = name,
                description = description,
                vida = vida,
                imageUrl = imageUrl,
                emptyList(),
                emptyList()
            )
            RetrofitClient.api.addBoss(newBoss)
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteBoss(id: Int): Boolean {
        return try {
            RetrofitClient.api.deleteBoss(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
