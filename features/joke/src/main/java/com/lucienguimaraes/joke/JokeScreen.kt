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
internal fun JokeScreen(
    modifier: Modifier = Modifier,
    jokeUIState: JokeUIState,
    onGetJokeClick: () -> Unit,
) {
    Surface(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = jokeUIState.joke?.content?.firstOrNull()?: stringResource(id = R.string.joke_placeholder),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.size(16.dp))
            val fetchJokeButtonState = rememberButtonState()
            fetchJokeButtonState.loading = jokeUIState.loading
            MainButton(
                modifier = Modifier,
                buttonState = fetchJokeButtonState,
                onClick = onGetJokeClick,
                text = stringResource(id = R.string.get_a_joke)
            )
        }
    }
}

@Composable
@ThemeModePreviews
fun JokeScreenPreview() {
    MyJokesTheme {
        JokeScreen(
            modifier = Modifier.fillMaxSize(),
            jokeUIState = JokeUIState(),
            onGetJokeClick = {},
        )
    }
}
