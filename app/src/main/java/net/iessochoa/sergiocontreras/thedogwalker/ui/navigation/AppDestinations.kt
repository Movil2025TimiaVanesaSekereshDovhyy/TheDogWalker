package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

import net.iessochoa.sergiocontreras.thedogwalker.R

object RecipeListDestination : NavigationDestination {
    override val route = "recipe_list"
    override val titleRes = R.string.app_name
}

// Destino 2: La pantalla secundaria
object RecipeDetailDestination : NavigationDestination {
    override val route = "recipe_detail"
    override val titleRes = R.string.detail_title // Ej. "Detalle del Plato"
}