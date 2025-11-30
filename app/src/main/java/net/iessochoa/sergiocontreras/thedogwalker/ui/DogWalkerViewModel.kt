package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

class DogWalkerViewModel : ViewModel() {
    // 1. ESTADO INTERNO (MutableStateFlow)
    // Inicializamos con la lista vacía o cargamos datos iniciales
    private val _uiState = MutableStateFlow(DogWalkerUiState())

    // 2. ESTADO PÚBLICO (StateFlow - Solo lectura)
    // Exponemos el flujo para que la UI se suscriba sin poder tocar la olla
    val uiState: StateFlow<DogWalkerUiState> = _uiState.asStateFlow()

    // 3. EVENTOS (Lógica de Negocio)
    // Funciones que la UI llamará cuando el comensal (usuario) interactúe

    // Ejemplo: El usuario hace clic en una receta de la lista
    fun onDogSelected(dog: Dog) {
        _uiState.update { currentState ->
            currentState.copy(selectedDog = dog)
        }
    }

    // Ejemplo: Limpiar la selección al volver atrás
    fun onBackToMenu() {
        _uiState.update { it.copy(selectedDog = null) }
    }

    fun onToggleSelected(type: String) {
        _uiState.update { currentState ->

            var newDog = currentState.selectedDog

            newDog = when (type) {
                "walk" -> newDog!!.copy(isWalked = true)
                "pee" -> newDog!!.copy(hasPeed = true)
                else -> newDog!!.copy(hasPooped = true)
            }

            currentState.copy(
                selectedDog = newDog
            )

        }
    }

}
