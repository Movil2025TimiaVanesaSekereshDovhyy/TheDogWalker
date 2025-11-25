package net.iessochoa.sergiocontreras.thedogwalker.data

import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

// REPOSITORIO (Singleton)
object DogRepository {
    // "Base de datos" en memoria
    private val localDogs = mutableListOf(
        Dog(1, "Doge", "Shiba Inu"),
        Dog(2, "Rex", "German Shepherd"),
        Dog(3, "Cheems", "Shiba Inu"),
        Dog(4, "Lassie", "Rough Collie"),
        Dog(5, "Hachiko", "Akita Inu")
    )

    // Obtener todos (normalmente devolvería un Flow, pero para empezar List está bien)
    fun getAllDogs(): List<Dog> {
        return localDogs.toList() // Devolvemos copia para seguridad
    }

    // Obtener uno por ID
    fun getDogById(id: Int): Dog? {
        return localDogs.find { it.id == id }
    }

    // Actualizar estado (lógica movida del VM al Repo)
    fun updateDogStatus(id: Int, type: String) {
        val index = localDogs.indexOfFirst { it.id == id }
        if (index != -1) {
            val currentDog = localDogs[index]
            val updatedDog = when (type) {
                "walk" -> currentDog.copy(isWalked = !currentDog.isWalked)
                "poop" -> currentDog.copy(hasPooped = !currentDog.hasPooped)
                "pee" -> currentDog.copy(hasPeed = !currentDog.hasPeed)
                else -> currentDog
            }
            localDogs[index] = updatedDog
        }
    }
}