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
}
