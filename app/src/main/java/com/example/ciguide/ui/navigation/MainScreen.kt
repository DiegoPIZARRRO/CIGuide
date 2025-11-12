package com.example.ciguide.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*

import com.example.ciguide.ui.bosses.BossesUI
import com.example.ciguide.ui.home.HomeScreen
import com.example.ciguide.ui.items.itemsScreen
import com.example.ciguide.ui.progression.Progression


sealed class Screen(val ruta: String, val title: String){
    object Home: Screen("home","Home")
    object Jefes: Screen("jefes", "Jefes")
    object Progresion: Screen("progresion","Progresion")
    object Items: Screen("items","Items")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    val screens=listOf(
        Screen.Home,
        Screen.Jefes,
        Screen.Progresion
    )
Scaffold(
    topBar = {
        TopAppBar(title = { Text("Calamity Guide") })

        TabRow(
            selectedTabIndex = navController.currentBackStackEntryAsState().value?.destination?.route.let { currentRoute ->
                screens.indexOfFirst { it.ruta == currentRoute }
            }.coerceAtLeast(0)
        ) {
            screens.forEach { screen ->
                Tab(
                    text = { Text(screen.title)},
                    selected = navController.currentDestination?.route == screen.ruta,
                    onClick = {
                        navController.navigate(screen.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
)
    {innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.Home.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.ruta) { HomeScreen(navController) }
            composable(Screen.Jefes.ruta) { BossesUI() }
            composable(Screen.Progresion.ruta) { Progression() }
            composable(Screen.Items.ruta) { itemsScreen() }
        }
    }
}

