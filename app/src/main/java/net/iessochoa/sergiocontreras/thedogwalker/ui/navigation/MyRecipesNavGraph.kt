package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation


//PASO 3: El navHost roto a propósito


@Composable
fun MyRecipesNavHost(
    navController: NavHostController,
    viewModel: RecipeViewModel, // ViewModel compartido (tiene el estado de toda la app)
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RecipeListDestination.route, // 1. ¿Dónde empieza la app?
        modifier = modifier
    ) {
        // --- PANTALLA 1: LISTA ---
        composable(route = RecipeListDestination.route) {
            RecipeListScreen(
                // Pasamos el ViewModel o el estado necesario
                viewModel = viewModel,
                // Evento de navegación: Al hacer click en una receta...
                onRecipeClick = { recetaSeleccionada ->
                    // 1. Guardamos la selección en el ViewModel (Shared State)
                    viewModel.onRecipeSelected(recetaSeleccionada)

                    // 2. Navegamos al detalle (sin pasar argumentos complejos, el VM ya sabe cuál es)
                    navController.navigate(RecipeDetailDestination.route)
                }
            )
        }

        // --- PANTALLA 2: DETALLE ---
        composable(route = RecipeDetailDestination.route) {
            RecipeDetailScreen(
                // Al usar el MISMO viewModel, esta pantalla ya sabe qué receta se seleccionó
                viewModel = viewModel,
                // Evento para volver atrás
                onBack = { navController.popBackStack() }
            )
        }
    }
}