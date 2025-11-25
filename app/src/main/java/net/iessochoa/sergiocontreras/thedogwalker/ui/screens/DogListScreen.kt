package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.DogCard

@Composable
fun DogListScreen(
    dogs: List<Dog>,
    onDogClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(contentPadding = PaddingValues(16.dp), modifier = modifier) {
        items(dogs) { dog ->
            DogCard(dog)
        }
    }
}