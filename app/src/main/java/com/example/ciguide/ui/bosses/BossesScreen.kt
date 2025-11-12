package com.example.ciguide.ui.bosses

import android.util.Xml
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(estado.cargando){
            CircularProgressIndicator()
        }
        //Para mostrar el estado de carga error
        estado.error?.let { errorMsg ->
            Text(
                text = errorMsg,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.error
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ){
            items(estado.bosses){boss ->
                BossCard(boss = boss,
                         onImageClick ={ jefeSeleccionado = boss }
                )
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

@Composable
fun BossCard(boss: Boss, onImageClick: (Boss) -> Unit){
    Card (
        modifier = Modifier.fillMaxWidth()
                           .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column ( modifier = Modifier.padding(16.dp)){
            Image(
               painter = painterResource(id = boss.imageRes),
               contentDescription = "Imagen de ${boss.name}",
               modifier = Modifier
                   .fillMaxWidth()
                   .height(200.dp)
                   .clickable { onImageClick(boss) },
                contentScale = ContentScale.Crop
            )

            Text(
                text = boss.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = boss.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Items recomendados: ",
                fontWeight = FontWeight.SemiBold
            )

            boss.itemrecommended.forEach { item ->
                Text(text = "- $item")
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
                .clickable{onDismiss()},
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = boss.imageRes),
                contentDescription = "Imagen completa de ${boss.name}",
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