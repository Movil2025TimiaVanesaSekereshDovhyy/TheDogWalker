package net.iessochoa.sergiocontreras.thedogwalker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.thedogwalker.R
import net.iessochoa.sergiocontreras.thedogwalker.model.Dog

@Composable
fun StatusIconsRow(
    dog: Dog,
    clickable: Boolean,
    onToggle: (String) -> Unit = {},
    iconSize: Dp = 24.dp
) {

    val iconWalk = ImageVector.vectorResource(R.drawable.sound_detection_dog_barking_24px)
    val iconPee = ImageVector.vectorResource(R.drawable.total_dissolved_solids_24px)
    val iconPoo = ImageVector.vectorResource(R.drawable.bath_outdoor_24px)

    Row {
        StatusIcon(iconWalk, dog.isWalked, "walk", clickable, onToggle, iconSize)
        Spacer(Modifier.width(8.dp))
        StatusIcon(iconPee, dog.isWalked, "walk", clickable, onToggle, iconSize)
        Spacer(Modifier.width(8.dp))
        StatusIcon(iconPoo, dog.isWalked, "walk", clickable, onToggle, iconSize)
    }
}