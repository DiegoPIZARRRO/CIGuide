package com.example.ciguide.Data.Model

import androidx.annotation.DrawableRes

data class Boss(
    val id: Int,
    val name: String,
    val description: String,
    val itemrecommended: List<String>,
    @DrawableRes val imageRes: Int
)