package com.lucienguimaraes.joke

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.R
import com.lucienguimaraes.design_system.ThemeModePreviews
import com.lucienguimaraes.design_system.button.MainButton
import com.lucienguimaraes.design_system.button.rememberButtonState

@Composable
fun JokeScreen(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.joke_placeholder),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.size(16.dp))
            val validateButtonState = rememberButtonState()
            MainButton(
                modifier = Modifier,
                buttonState = validateButtonState,
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.get_a_joke)
            )
        }
    }
}

@Composable
@ThemeModePreviews
fun JokeScreenPreview() {
    MyJokesTheme {
        JokeScreen(Modifier.fillMaxSize())
    }
}
