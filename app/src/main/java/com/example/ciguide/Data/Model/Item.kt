package com.example.ciguide.Data.Model

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    @SerializedName("item_name")
    val name: String,
    val description: String?,

    @SerializedName("image_url")
    val imageUrl: String?
)
