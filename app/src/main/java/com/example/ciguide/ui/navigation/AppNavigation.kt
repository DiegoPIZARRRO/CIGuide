package com.example.ciguide.ui.navigation

import com.example.ciguide.ui.home.HomeScreen
import com.example.ciguide.ui.navigation.MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

        NavHost(navController = navController,
                startDestination = "home"
        ){
            composable("home"){
                HomeScreen(navController = navController)
        }
            composable("main"){
                MainScreen()
            }


    }
}
