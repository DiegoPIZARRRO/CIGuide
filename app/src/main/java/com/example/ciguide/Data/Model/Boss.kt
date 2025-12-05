package com.example.ciguide.Data.Model

import com.google.gson.annotations.SerializedName

data class Boss(
    @SerializedName("boss_id")
    val id: Int,

    @SerializedName("boss_name")
    val name: String,

    @SerializedName("boss_description")
    val description: String?,

    val vida: String?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("item_recommended")
    val itemRecommended: List<String>?,

    @SerializedName("item_description")
    val itemDescription: List<String>?
)
