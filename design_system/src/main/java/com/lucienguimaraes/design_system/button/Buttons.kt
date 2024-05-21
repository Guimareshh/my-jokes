package com.lucienguimaraes.design_system.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.ThemeModePreviews
import com.lucienguimaraes.design_system.animation.PulsingDotInfiniteProgress

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    buttonState: ButtonState = rememberButtonState(),
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        enabled = buttonState.enable,
        onClick = { if (buttonState.canBeClicked()) onClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        if (buttonState.loading) {
            PulsingDotInfiniteProgress()
        } else {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
@ThemeModePreviews
fun MainButtonPreview() {
    MyJokesTheme {
        MainButton(onClick = {}, text = "Click me!")
    }
}

@Composable
@ThemeModePreviews
fun MainButtonLoadingPreview() {
    MyJokesTheme {
        MainButton(
            onClick = {},
            text = "Click me!",
            buttonState = rememberButtonState(loading = true),
        )
    }
}
