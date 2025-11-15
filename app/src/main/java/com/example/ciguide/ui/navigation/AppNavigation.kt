package com.example.ciguide.ui.navigation

import com.example.ciguide.ui.home.HomeScreen
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.ciguide.ui.secret.secretScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Home.ruta
        ){
            composable(Screen.Home.ruta){
                HomeScreen(navController = navController)
        }
            composable(Screen.Main.ruta){
                MainScreen()
            }
            composable(Screen.Secret.ruta){
                secretScreen()
            }


    }
}
