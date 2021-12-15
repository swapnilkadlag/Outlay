package com.sk.outlay.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryDark,
    secondary = accentDark,
    secondaryVariant = accentDark,
    background = backgroundDark1,
    surface = backgroundDark3,
    error = Color.Red,
    onPrimary = black,
    onSecondary = black,
    onBackground = white,
    onSurface = white,
    onError = white,
)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryLight,
    secondary = accentLight,
    secondaryVariant = accentLight,
    background = backgroundLight1,
    surface = backgroundLight3,
    error = Color.Red,
    onPrimary = black,
    onSecondary = black,
    onBackground = black,
    onSurface = black,
    onError = black,
)

@Composable
fun OutlayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}