package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.iessochoa.sergiocontreras.thedogwalker.R
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogDetailDestination
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogListDestination
import net.iessochoa.sergiocontreras.thedogwalker.ui.navigation.DogWalkerNavGraph
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogWalkerApp(
    viewModel: DogWalkerViewModel = viewModel(), // Inyectamos/Creamos el VM aquí
    navController: NavHostController = rememberNavController() // Creamos el Controller
) {
    // Trucazo 🤙: Observamos la ruta actual para cambiar el título de la TopBar
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Lógica para decidir qué título mostrar según la ruta donde estemos
    val currentScreenTitle = when (currentRoute) {
        DogListDestination.route -> stringResource(DogListDestination.titleRes)
        DogDetailDestination.route -> stringResource(DogDetailDestination.titleRes)
        else -> stringResource(R.string.app_name)
    }

    // Lógica para saber si podemos volver atrás (para mostrar la flechita)
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            DogWalkerTopAppBar(
                title = currentScreenTitle,
                canNavigateBack = canNavigateBack,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        // Aquí llamamos a nuestro NavHost personalizado
        DogWalkerNavGraph(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DogWalkerTopAppBar(
        title: String,
        canNavigateBack: Boolean,
        modifier: Modifier = Modifier,
        scrollBehavior: TopAppBarScrollBehavior? = null,
        navigateUp: () -> Unit = {}
    ) {
        CenterAlignedTopAppBar(
            title = { Text(title) },
            modifier = modifier,
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }




