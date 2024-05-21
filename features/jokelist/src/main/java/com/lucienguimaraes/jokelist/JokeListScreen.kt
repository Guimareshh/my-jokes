package com.lucienguimaraes.jokelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.R
import com.lucienguimaraes.design_system.ThemeModePreviews

@Composable
internal fun JokeListScreen(
    modifier: Modifier = Modifier,
    jokeListUIState: JokeListUIState,
    onDeleteClick: (Long) -> Unit,
    onDismissDialog: () -> Unit,
) {
    Surface(modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(items = jokeListUIState.jokeList, key = { joke -> joke.id }) { joke ->
                JokeCard(joke = joke, onDeleteClick = { onDeleteClick(joke.id) })
            }
        }
    }
    if (jokeListUIState.error) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.generic_error_message),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            confirmButton = {
                TextButton(onClick = onDismissDialog) {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            },
            onDismissRequest = onDismissDialog,
        )
    }
}

@Composable
@ThemeModePreviews
internal fun JokeScreenPreview() {
    MyJokesTheme {
        JokeListScreen(
            modifier = Modifier.fillMaxSize(),
            jokeListUIState = JokeListUIState(),
            onDeleteClick = {},
            onDismissDialog = {},
        )
    }
}
