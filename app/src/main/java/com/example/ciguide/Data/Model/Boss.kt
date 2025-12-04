package com.example.ciguide.Data.Model

import com.google.gson.annotations.SerializedName
data class Boss(
    val id: Int,
    val name: String,
    val description: String,
    val vida: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("item_recomended")
    val itemRecomended: List<String> = emptyList(),

    @SerializedName("item_description")
    val itemDescription: List<String> = emptyList()

)