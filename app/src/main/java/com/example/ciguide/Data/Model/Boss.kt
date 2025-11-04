package com.example.ciguide.Data.Model

data class Boss(
    val id: Int,
    val name: String,
    val description: String,
    val itemrecommended: List<String>
)