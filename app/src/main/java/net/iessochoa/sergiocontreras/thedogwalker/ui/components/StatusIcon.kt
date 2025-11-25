package net.iessochoa.sergiocontreras.thedogwalker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.ui.theme.TheDogWalkerTheme

@Composable
fun StatusIcon(
    icon: ImageVector,
    isDone: Boolean,
    type: String,
    clickable: Boolean,
    onToggle: (String) -> Unit,
    size: Dp
) {
    val color = if (isDone) Color.Green else Color.Gray
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = color,
        modifier = Modifier
            .size(size)
            .then(if (clickable) Modifier.clickable { onToggle(type) } else Modifier)
    )
}

@Preview
@Composable
private fun PreviewStatusIcon() {
    TheDogWalkerTheme() {
        StatusIcon(
            icon = Icons.Default.Call,
            isDone = false,
            type = "Call",
            clickable = true,
            onToggle = {},
            size = 24.dp
        )
    }
}