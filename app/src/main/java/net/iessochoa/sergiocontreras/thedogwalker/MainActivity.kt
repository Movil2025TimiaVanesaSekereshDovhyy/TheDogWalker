package net.iessochoa.sergiocontreras.thedogwalker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.thedogwalker.ui.DogWalkerApp
import net.iessochoa.sergiocontreras.thedogwalker.ui.theme.TheDogWalkerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDogWalkerTheme {
                DogWalkerApp()
            }
        }
    }
}
