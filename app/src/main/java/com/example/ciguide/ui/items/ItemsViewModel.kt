package com.example.ciguide.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciguide.Data.Model.Item
import com.example.ciguide.Data.Repository.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Data class para representar el estado de la UI
data class ItemsUiState(
    val items: List<Item> = emptyList(),
    val cargando: Boolean = false,
    val error: String? = null
)

class ItemsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ItemsUiState())
    val uiState: StateFlow<ItemsUiState> = _uiState.asStateFlow()

    private val repository = ItemRepository()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true) }
            try {
                val itemsList = repository.getItems()
                _uiState.update {
                    it.copy(cargando = false, items = itemsList)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(cargando = false, error = e.message)
                }
            }
        }
    }
}
