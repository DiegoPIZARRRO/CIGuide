package com.example.ciguide.ui.bosses

import com.example.ciguide.Data.Model.Boss
import com.example.ciguide.Data.Repository.WikiRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class BossesViewModelTest {

    private val mockRepository: WikiRepository = mockk()

    private lateinit var viewModel: BossViewModel

    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    //primero para comprobar si carga las listas de los jefes
    @Test
    fun `cuando el viewModel se inicia, el estado debe contener la lista de jefes`() = runTest {

        val fakeBosses = listOf(
            Boss(1, "Jefe 1", "Desc 1", "100", "url1", null, null),
            Boss(2, "Jefe 2", "Desc 2", "200", "url2", null, null)
        )

        coEvery { mockRepository.getBosses() } returns fakeBosses

        viewModel = BossViewModel(mockRepository)

        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.first()
        assertEquals(fakeBosses, uiState.bosses)
        assertEquals(false, uiState.cargando)
        assertNull(uiState.error)
    }

    //manejo de errores del viewmodel
    @Test
    fun `cuando el repositorio lanza una excepcion, el estado debe contener un error`() = runTest {
        val errorMessage = "Error de red"
        coEvery { mockRepository.getBosses() } throws RuntimeException(errorMessage)

        viewModel = BossViewModel(mockRepository)

        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.first()
        assertEquals("Error al cargar los jefes: $errorMessage", uiState.error)
        assertEquals(false, uiState.cargando)
        assertEquals(true, uiState.bosses.isEmpty())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}