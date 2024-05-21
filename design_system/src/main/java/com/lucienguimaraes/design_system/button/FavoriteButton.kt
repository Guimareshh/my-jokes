package com.lucienguimaraes.design_system.button

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.ThemeModePreviews

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    favorite: Boolean,
    onClick: () -> Unit,
) {

    val scale = remember { Animatable(1f) }
    var first by remember { mutableStateOf(true) }

    LaunchedEffect(favorite) {
        if (first.not()) {
            scale.snapTo(0.7f)
            scale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
            )
        } else {
            first = false
        }
    }

    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            modifier = Modifier.scale(scale.value),
            imageVector = if (favorite) {
                Icons.Rounded.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
@ThemeModePreviews
internal fun FavoriteButtonPreview() {
    MyJokesTheme {
        Surface {
            Box {
                var favorite by remember { mutableStateOf(false) }
                FavoriteButton(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center),
                    favorite = favorite,
                    onClick = { favorite = favorite.not() },
                )
            }
        }
    }
}
