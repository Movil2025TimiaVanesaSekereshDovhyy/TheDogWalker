package net.iessochoa.sergiocontreras.thedogwalker.ui

import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

data class DogWalkerUiState(
    val dogs: List<Dog> = emptyList(),
    val selectedDog: Dog? = null
)