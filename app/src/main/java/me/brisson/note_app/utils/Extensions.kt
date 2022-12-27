package me.brisson.note_app.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun Color.Companion.random(): Color {
    val random = Random

    val highsAndLowValues = listOf(
        Pair(150, 220),
        Pair(150, 220),
        Pair(0, 50)
    ).shuffled()

    return Color(
        red = random.nextInt(highsAndLowValues[0].first, highsAndLowValues[0].second),
        green = random.nextInt(highsAndLowValues[1].first, highsAndLowValues[1].second),
        blue = random.nextInt(highsAndLowValues[2].first, highsAndLowValues[2].second),
    )
}