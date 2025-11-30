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
    private val _uiState = MutableStateFlow(DogWalkerUiState(dogs = DogRepository.getAllDogs()))

    // 2. ESTADO PÚBLICO (StateFlow - Solo lectura)
    // Exponemos el flujo para que la UI se suscriba sin poder tocar la olla
    val uiState: StateFlow<DogWalkerUiState> = _uiState.asStateFlow()

    // 3. EVENTOS (Lógica de Negocio)
    // Funciones que la UI llamará cuando el comensal (usuario) interactúe

    // Ejemplo: El usuario hace clic en una receta de la lista
    fun onDogSelected(id: Int) {
        val dog = DogRepository.getDogById(id)
        _uiState.update { currentState ->
            currentState.copy(selectedDog = dog)
        }
    }

    // Ejemplo: Limpiar la selección al volver atrás
    fun onBackToMenu() {
        _uiState.update { it.copy(selectedDog = null) }
    }

    fun onToggleStatus(type: String){
        val selected = _uiState.value.selectedDog ?: return
        DogRepository.updateDogStatus(selected.id, type) //Se llama al Repository y se actualiza el perro
        val updatedDog = DogRepository.getDogById(selected.id) //Perro actualizado
        _uiState.update {
            it.copy(
                dogs = DogRepository.getAllDogs(),
                selectedDog = updatedDog
            )
        }
    }

}
