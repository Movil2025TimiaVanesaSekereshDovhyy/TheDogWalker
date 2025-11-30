package net.iessochoa.sergiocontreras.thedogwalker.ui

import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

data class DogWalkerUiState (
    val dogs: List<Dog> = emptyList(),    //La lista completa para el menú
    val selectedDog: Dog? = null,      // El plato que se está cocinando/viendo
    val isLoading: Boolean = false           // ¿Estamos esperando ingredientes?
)