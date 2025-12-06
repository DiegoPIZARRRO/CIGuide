package com.example.ciguide.ui.bosses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciguide.Data.Model.Boss
import com.example.ciguide.Data.Repository.WikiRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class uiStateBosses(
    val cargando: Boolean = false,
    val bosses: List<Boss> = emptyList(),
    val error: String? = null
)

class BossViewModel(private val repository: WikiRepository = WikiRepository()) : ViewModel(){
    private val _uiState = MutableStateFlow(uiStateBosses())

    val uiState: StateFlow<uiStateBosses> = _uiState.asStateFlow()

    init {
        loadBosses()
    }

    fun loadBosses() {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true) }
            try {
                val bosslist = repository.getBosses()
                _uiState.update { it.copy(cargando = false, bosses = bosslist, error = null) }

            }catch (e: Exception){
                _uiState.update { it.copy(cargando = false, error = "Error al cargar los jefes: ${e.message}") }
            }
        }
    }

    fun deleteBoss(id: Int) {
        viewModelScope.launch {
            try {
                val exito = repository.deleteBoss(id)

                if (exito) {
                    loadBosses()
                } else {
                    _uiState.update { it.copy(error = "Error al eliminar el jefe") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error al eliminar el jefe: ${e.message}") }
            }
        }
    }
}

