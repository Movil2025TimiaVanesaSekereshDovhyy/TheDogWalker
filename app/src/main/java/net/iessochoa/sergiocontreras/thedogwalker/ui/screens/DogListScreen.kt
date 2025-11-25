package net.iessochoa.sergiocontreras.thedogwalker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.DogCard
import net.iessochoa.sergiocontreras.thedogwalker.ui.components.StatusIconsRow
import net.iessochoa.sergiocontreras.thedogwalker.ui.theme.Typography

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