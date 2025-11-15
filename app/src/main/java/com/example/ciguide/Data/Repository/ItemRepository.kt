package com.example.ciguide.Data.Repository

import com.example.ciguide.Data.Model.Item
import com.example.ciguide.R

class ItemRepository{
    private val itemsList:List<Item> = listOf(
        Item("Arca de los elementos", "Una espada poderosa post-Providence. Dispara proyectiles elementales.",R.drawable.arca_de_los_elementos),
        Item("Rayo alfa", "Un rifle de energía post-Devorador que dispara rayos láser de alta velocidad.",R.drawable.rayoalfa),
        Item("Diseminador", "Escopeta post-Moon Lord que dispara una ráfaga de balas de energía.",R.drawable.disseminator),
        Item("Exoblade", "Espada definitiva del modo, requiere materiales de Exo Mechs. Lanza rayos de energía pura.",R.drawable.exoblade),
        Item("Muramasa", "Espada clásica, ahora parte de crafteos complejos y mejorada en Calamity.",R.drawable.murasama),
        Item("Fotoviscerador", "Un bumerán de luz post-Plantera que ignora la inmunidad de cuadros.",R.drawable.photoviscerator),
        Item("Inmaterializador cosmico", "Arma de invocacion cual se consigue con materiales de exo-mecanicos",R.drawable.cosmic_immaterializer)
    )
    fun getitems(): List<Item>{
        return itemsList
    }
}