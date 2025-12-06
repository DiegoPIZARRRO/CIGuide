package com.example.ciguide.ui.bosses

import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ciguide.Data.Model.Boss

@Composable
fun BossesUI(viewModel: BossViewModel = viewModel()){
    val estado by viewModel.uiState.collectAsState()
    var jefeSeleccionado by remember { mutableStateOf<Boss?>(null) }

    var showaddScreen by remember { mutableStateOf(false) }

    if (showaddScreen){
        AddBossScreen(
            onBack = {showaddScreen = false},
            onBossAdded = { viewModel.loadBosses() })
    }else {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showaddScreen = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ){
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir jefe")
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (estado.error != null) {
                    Text(
                        text = estado.error!!,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                } else if (estado.cargando) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(estado.bosses) { boss ->
                            BossCard(
                                boss = boss,
                                onImageClick = { jefeSeleccionado = boss },
                                onDeleteClick = { viewModel.deleteBoss(boss.id) }
                            )
                        }
                    }
                }
                jefeSeleccionado?.let { boss ->
                    ZoomImages(
                        boss = boss,
                        onDismiss = { jefeSeleccionado = null }
                    )
                }
            }
        }

    }
}

@Composable
fun BossCard(boss: Boss, onImageClick: (Boss) -> Unit, onDeleteClick: (Int) -> Unit){
    Card (
        modifier = Modifier.fillMaxWidth()
                           .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
      Box(modifier = Modifier.fillMaxWidth()) {
          Column ( modifier = Modifier.padding(16.dp)){
              AsyncImage(
                  model = boss.imageUrl,
                  contentDescription = boss.name,
                  modifier = Modifier
                      .fillMaxWidth()
                      .height(200.dp),
                  contentScale = ContentScale.Fit
              )
              Text(
                  text = boss.name,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(text = boss.description ?: "")
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                  text = "Items recomendados: ",
                  fontWeight = FontWeight.SemiBold
              )

              boss.itemRecommended?.forEach { item ->
                  Text(text = "- $item")
              }
          }
          IconButton(
              onClick = { onDeleteClick(boss.id) },
              modifier = Modifier.align(Alignment.TopEnd)
          ) {
              Icon(
                  imageVector = Icons.Default.Delete,
                  contentDescription = "Eliminar jefe",
                  tint = MaterialTheme.colorScheme.error
              )
          }
      }
    }
}

@Composable
fun ZoomImages(boss: Boss, onDismiss: () -> Unit){
    var escala by remember { mutableStateOf(1f) }
    var ejex by remember { mutableStateOf(0f) }
    var ejey by remember { mutableStateOf(0f) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.85f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = boss.imageUrl,
                contentDescription = "Imagen ${boss.name}",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = escala,
                        scaleY = escala,
                        translationX = ejex,
                        translationY = ejey
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            escala *= zoom
                            escala = escala.coerceIn(0.5f, 3f)

                            ejex += pan.x * escala
                            ejey += pan.y * escala

                        }
                    }
            )
        }
    }
}