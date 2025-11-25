package net.iessochoa.sergiocontreras.thedogwalker.model

import androidx.annotation.DrawableRes
import net.iessochoa.sergiocontreras.thedogwalker.R

/**
 * Representa un perro con su información.
 *
 * @property id Identificador único del perro.
 * @property name Nombre del perro.
 * @property breed Raza del perro.
 * @property isWalked Indica si el perro ha sido paseado.
 * @property hasPooped Indica si el perro ha hecho caca.
 * @property hasPeed Indica si el perro ha hecho pis.
 */
data class Dog(
    val id: Int,
    val name: String,
    val breed: String,
    val isWalked: Boolean = false,
    val hasPooped: Boolean = false,
    val hasPeed: Boolean = false,
    @param:DrawableRes val imageRes: Int = R.drawable.ic_launcher_foreground
)
