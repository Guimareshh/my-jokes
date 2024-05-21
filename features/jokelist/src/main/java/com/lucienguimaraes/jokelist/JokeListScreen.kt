package com.lucienguimaraes.jokelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.ThemeModePreviews

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun JokeListScreen(
    modifier: Modifier = Modifier,
    jokeListUIState: JokeListUIState,
    onDeleteClick: (Long) -> Unit,
) {
    Surface(modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(items = jokeListUIState.jokeList, key = { joke -> joke.id }) { joke ->
                JokeCard(
                    modifier = Modifier.animateItemPlacement(),
                    joke = joke,
                    onDeleteClick = { onDeleteClick(joke.id) },
                )
            }
        }
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
        )
    }
}
