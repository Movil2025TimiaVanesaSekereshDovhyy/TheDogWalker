package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

interface NavigationDestination {
    /**
     * String único que define la ruta (ej: "lista_recetas")
     * Es la dirección interna que usa Android para saber dónde ir.
     */
    val route: String

    /**
     * ID del recurso de texto que se mostrará en la TopAppBar (ej: R.string.app_name). ¡Ojo en el detalle del plato mostramos el nombre del plato que no es un texto estático! Por simplicidad se puede manejar en la lógica del TopAppBar --> Si estoy en home screen uso titleRes (R.string...) else (detalle) leo título del ViewModel
     */
    val titleRes: Int
}