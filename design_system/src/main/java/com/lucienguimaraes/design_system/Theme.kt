package com.lucienguimaraes.design_system


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.typography.appTypography

private val DarkColorPalette = darkColors(
    primary = MyJokesColor.Green.base,
    primaryVariant = MyJokesColor.Green.darkenVariant,
    secondary = MyJokesColor.Green.base,
    background = Color.Black,
    surface = MyJokesColor.Grey.eerieBlack,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = MyJokesColor.Red.darken,
)

private val LightColorPalette = lightColors(
    primary = MyJokesColor.Green.base,
    primaryVariant = MyJokesColor.Green.sea,
    secondary = MyJokesColor.Green.base,
    background = MyJokesColor.Grey.cultureLight,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = MyJokesColor.Grey.base,
    onSurface = MyJokesColor.Grey.base,
    error = MyJokesColor.Red.darken,
)

private val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun MyJokesTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = appTypography,
        shapes = shapes,
        content = content,
    )
}
