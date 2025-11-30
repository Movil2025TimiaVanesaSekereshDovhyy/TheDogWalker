package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerViewModel
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogDetailScreen
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


//PASO 3: El navHost roto a propósito para practicar

@Composable
fun DogWalkerNavGraph(
    navController: NavHostController,
    viewModel: DogWalkerViewModel, // ViewModel compartido (tiene el estado de toda la app)
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DogListDestination.route, // 1. ¿Dónde empieza la app?
        modifier = modifier
    ) {
        // --- PANTALLA 1: LISTA ---
        composable(route = DogListDestination.route) {
            DogListScreen(
                // Pasamos el ViewModel o el estado necesario
                viewModel = viewModel,
                // Evento de navegación: Al hacer click en una receta...
                onDogClicked = { dog ->
                    // 1. Guardamos la selección en el ViewModel (Shared State)
                    viewModel.onDogSelected(dog)

                    // 2. Navegamos al detalle (sin pasar argumentos complejos, el VM ya sabe cuál es)
                    navController.navigate(DogDetailDestination.route)
                }
            )
        }

        // --- PANTALLA 2: DETALLE ---
        composable(route = DogDetailDestination.route) {
            DogDetailScreen(
                // Al usar el MISMO viewModel, esta pantalla ya sabe qué receta se seleccionó
                viewModel = viewModel,
                // Evento para volver atrás
                onBack = { navController.popBackStack() }
            )
        }
    }
}
