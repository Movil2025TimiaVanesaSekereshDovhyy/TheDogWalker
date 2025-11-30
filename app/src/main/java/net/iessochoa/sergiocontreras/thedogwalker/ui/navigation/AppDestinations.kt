package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

import net.iessochoa.sergiocontreras.thedogwalker.R

object DogListDestination : NavigationDestination {
    override val route = "dog_list"
    override val titleRes = R.string.app_name
}

// Destino 2: La pantalla secundaria
object DogDetailDestination : NavigationDestination {
    override val route = "dog_detail"
    override val titleRes = R.string.detail_title // Ej. "Detalle del Plato"
}