package net.iessochoa.sergiocontreras.thedogwalker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.ui.screens.DogListScreen

@Composable
fun DogWalkerApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        DogListScreen(
            DogRepository.getAllDogs(),
            {},
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )

    }
}