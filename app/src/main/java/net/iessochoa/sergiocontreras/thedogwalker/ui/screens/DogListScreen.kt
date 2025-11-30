package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerViewModel
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.DogCard

@Composable
fun DogListScreen(
    //dogs: List<Dog>,
    onDogClicked: (Dog) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DogWalkerViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val dogs = uiState.dogs

    LazyColumn(contentPadding = PaddingValues(16.dp), modifier = modifier) {
        items(dogs) { dog ->
            DogCard(dog, onDogClicked = onDogClicked)
        }
    }
}