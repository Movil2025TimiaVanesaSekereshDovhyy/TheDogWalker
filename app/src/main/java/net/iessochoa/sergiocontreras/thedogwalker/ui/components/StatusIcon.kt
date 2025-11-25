package net.iessochoa.sergiocontreras.thedogwalker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun StatusIcon(
    icon: ImageVector,
    isDone: Boolean,
    type: String,
    clickable: Boolean,
    onToggle: (String) -> Unit,
    size: Int
) {
    val color = if (isDone) Color.Green else Color.Gray
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(size.dp)
            .then(if (clickable) Modifier.clickable { onToggle(type) } else Modifier)
    )
}