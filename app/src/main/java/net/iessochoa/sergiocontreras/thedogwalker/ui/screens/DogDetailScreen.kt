package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.thedogwalker.R
import net.iessochoa.sergiocontreras.thedogwalker.data.DogRepository
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerViewModel
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.StatusIconsRow
import net.iessochoa.sergiocontreras.thedogwalker.ui.theme.TheDogWalkerTheme

@Composable
fun DogDetailScreen(
    //dog: Dog,
    //onToggleStatus: (String) -> Unit, // Callback para comunicar al ViewModel
    modifier: Modifier = Modifier,
    viewModel: DogWalkerViewModel = viewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val dog = uiState.selectedDog ?: uiState.dogs.first()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. FOTO DE PERFIL (Grande y circular)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .padding(4.dp) // Espacio entre borde e imagen
        ) {
            Image(
                painter = painterResource(id = dog.imageRes),
                contentDescription = "Foto de ${dog.name}",
                contentScale = ContentScale.Crop, // Recorta para llenar el círculo
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2. INFORMACIÓN DEL PERRO
        Text(
            text = dog.name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = dog.breed,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. TARJETA DE CONTROL DE ESTADO
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registro del Paseo",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Reutilizamos tu StatusIconsRow (con iconos grandes e interactivos)
                StatusIconsRow(
                    dog = dog,
                    clickable = true,
                    onToggle = {viewModel.onToggleSelected(it) },
                    iconSize = 64.dp // Hacemos los iconos grandes para facilitar el 'tap'
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Toca los iconos para marcar/desmarcar",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun DogDetailScreenPreview() {
//    val previewDog = Dog(1, "Doge", "Shiba Inu", imageRes = R.drawable.shiba_inu)
//    TheDogWalkerTheme() {
//        DogDetailScreen(
//            dog = previewDog,
//            onToggleStatus =  {it}
//        )
//
//    }
//}