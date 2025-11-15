package com.example.ciguide.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*


sealed class Screen(val ruta: String, val title: String, val icon: ImageVector){
    object Main: Screen("main", "Main", Icons.Default.Star)
    object Home: Screen("home","Home", Icons.Default.Home)
    object Jefes: Screen("jefes", "Jefes", Icons.Default.Layers)
    object Progresion: Screen("progresion", "Progresion", Icons.Default.DateRange)
    object Items: Screen("items", "Items", Icons.Default.Colorize)
    object Secret: Screen("secret", "Secret", Icons.Default.Lock)

}