package com.example.ciguide.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ciguide.R

import com.example.ciguide.ui.bosses.BossesUI
import com.example.ciguide.ui.items.itemsScreen
import com.example.ciguide.ui.progression.Progression
import com.example.ciguide.ui.secret.secretScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

Scaffold(
    topBar = {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.height(65.dp)

                )},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF8B221B),
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
    },

    bottomBar={
        val screens = listOf(
            Screen.Jefes,
            Screen.Progresion,
            Screen.Items
        )

        NavigationBar{
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            screens.forEach { screen ->
                NavigationBarItem(
                    label= {Text(screen.title)},
                    icon = {Icon(screen.icon, contentDescription = screen.title)},
                    selected = currentDestination?.hierarchy?.any {it.route == screen.ruta} == true,
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
            startDestination = Screen.Jefes.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Jefes.ruta) { BossesUI() }
            composable(Screen.Progresion.ruta) { Progression(navController = navController) }
            composable(Screen.Items.ruta) { itemsScreen() }
            composable(Screen.Secret.ruta){secretScreen()}
        }
    }
}

