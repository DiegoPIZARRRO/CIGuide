package com.example.ciguide.Data.Repository

import android.util.Log
import com.example.ciguide.Data.Model.Item
import com.example.ciguide.Data.Network.RetrofitClient
import com.example.ciguide.R

class ItemRepository{
    suspend fun getItems(): List<Item>{
        return try {
            val respuesta = RetrofitClient.api.getItems()
            Log.d("API_ITEM", "Se recibieron ${respuesta.size} items")
            respuesta
        } catch (e: Exception){
            Log.e("API_ITEM", "Error al cargar items: ${e.message}")
            throw e
        }
    }
}