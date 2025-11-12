package com.example.ciguide.ui.navigation

import com.example.ciguide.ui.home.HomeScreen
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

        NavHost(startDestination = "home",
                navController = navController
        ){
            composable("home"){
                HomeScreen(navController = navController)
        }
            composable("main"){
                MainScreen()
            }


    }
}
