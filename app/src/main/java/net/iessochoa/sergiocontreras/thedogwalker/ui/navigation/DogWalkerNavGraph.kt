package net.iessochoa.sergiocontreras.thedogwalker.ui.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerViewModel
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogDetailScreen
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen


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
        composable(DogListDestination.route) {
            val uiState = viewModel.uiState.value
            DogListScreen(
                // Pasamos el ViewModel o el estado necesario
                dogs = uiState.dogs,
                // Evento de navegación: Al hacer click en una receta...
                onDogClick = { id ->
                    // 1. Guardamos la selección en el ViewModel (Shared State)
                    viewModel.onDogSelected(id)

                    // 2. Navegamos al detalle (sin pasar argumentos complejos, el VM ya sabe cuál es)
                    navController.navigate(DogDetailDestination.route)
                }
            )
        }

        // --- PANTALLA 2: DETALLE ---
        composable(route = DogDetailDestination.route) {
                val uiState = viewModel.uiState.value
                // Al usar el MISMO viewModel, esta pantalla ya sabe qué receta se seleccionó
                uiState.selectedDog?.let { dog ->
                    DogDetailScreen(
                        dog = dog,
                        onToggleStatus = { type -> viewModel.onToggleStatus(type)}
                    )
                }
        }
    }
}