package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

@Composable
fun DogListScreen(dogs: List<Dog>, onDogClick: (Int) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(dogs) { dog ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onDogClick(dog.id) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    // Placeholder para foto
                    Box(modifier = Modifier.size(60.dp)) { Icon(Icons.Default.Face, null, Modifier.fillMaxSize()) }
                    Spacer(Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = dog.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = dog.breed, style = MaterialTheme.typography.bodyMedium)
                    }
                    // Iconos de estado (Solo lectura)
                    StatusIconsRow(dog, clickable = false)
                }
            }
        }
    }
}