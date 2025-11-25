package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

interface NavigationDestination {
    // TODO: TAREA 3 - Define las propiedades de la interfaz
}

// Destino 1: La pantalla principal (Lista de Perros)
object DogListDestination : NavigationDestination {
    // TODO: Implementa las propiedades de la interfaz para la pantalla de lista
    // Pista: La ruta puede ser "patient_list"
}

// Destino 2: La pantalla secundaria (Detalle del Perro)
object DogDetailDestination : NavigationDestination {
    // TODO: Implementa las propiedades de la interfaz para la pantalla de detalle
    // Pista: La ruta puede ser "patient_detail"
}