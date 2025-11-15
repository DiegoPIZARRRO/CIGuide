package com.example.ciguide.ui.home

import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.ciguide.ui.navigation.Screen


@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guía de Calamity",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "wiki 100% real no fake",
            fontSize = 16.sp,
            color = Color(0xFFF37F20)
        )
        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = {
                navController.navigate(Screen.Main.ruta) {
                    popUpTo(Screen.Home.ruta) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF37F20),
                contentColor = Color.White
            ))
        {
            Text("Comenzar")
        }
    }
}