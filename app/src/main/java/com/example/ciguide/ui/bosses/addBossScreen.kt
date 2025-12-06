package com.example.ciguide.ui.bosses

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ciguide.Data.Repository.WikiRepository
import kotlinx.coroutines.launch

@Composable
fun AddBossScreen(onBack: () -> Unit, onBossAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var vida by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    var isSaving by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val repository = remember { WikiRepository() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Agregar jefe", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Jefe") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción / Lore") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            maxLines = 5
        )

        OutlinedTextField(
            value = vida,
            onValueChange = { vida = it },
            label = { Text("Vida (Ej: 500,000)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Link de Imagen (URL)") },
            placeholder = { Text("https://...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isEmpty() || vida.isEmpty() || description.isEmpty() || imageUrl.isEmpty()) {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    isSaving = true
                    scope.launch {
                        val exito = repository.addBoss(name, description, vida, imageUrl)
                        isSaving = false
                        if (exito) {
                            Toast.makeText(context, "Jefe agregado con éxito", Toast.LENGTH_SHORT).show()
                            onBossAdded()
                            onBack()
                        } else {
                            Toast.makeText(context, "Error al agregar el jefe", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            enabled = !isSaving,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            if(isSaving){
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Guardando")
            } else {
                Text("Guardar")
            }
        }
        TextButton(onClick =  onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Cancelar")
        }
    }
}