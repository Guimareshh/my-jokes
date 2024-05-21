package com.lucienguimaraes.design_system.animation

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun PulsingDotInfiniteProgress(
    colorDot: Color = MaterialTheme.colorScheme.onPrimary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "PulsingDotInfiniteProgress")
    Row {
        infiniteTransition.PulsingDot(colorDot , StartOffset(0))
        infiniteTransition.PulsingDot(colorDot, StartOffset(150, StartOffsetType.FastForward))
        infiniteTransition.PulsingDot(colorDot, StartOffset(300, StartOffsetType.FastForward))
    }
}

@Composable
fun InfiniteTransition.PulsingDot(colorDot: Color, startOffset: StartOffset) {
    val scale by animateFloat(
        0.2f,
        1f,
        infiniteRepeatable(tween(600), RepeatMode.Reverse, initialStartOffset = startOffset),
        label = "PulsingDot",
    )
    Box(
        Modifier.padding(5.dp).size(12.dp).graphicsLayer {
            scaleX = scale
            scaleY = scale
        }.background(colorDot, shape = CircleShape)
    )
}
