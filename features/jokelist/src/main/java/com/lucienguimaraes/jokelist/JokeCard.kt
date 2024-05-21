package com.lucienguimaraes.jokelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.entities.jokeEntityFavorite
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.ThemeModePreviews

@Composable
fun JokeCard(modifier: Modifier = Modifier, joke: JokeEntity, onDeleteClick: () -> Unit) {
    Card {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = joke.content,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.size(16.dp))
            IconButton(modifier = modifier.size(48.dp), onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
@ThemeModePreviews
internal fun JokeCardPreview() {
    MyJokesTheme {
        JokeCard(
            modifier = Modifier.fillMaxSize(),
            joke = jokeEntityFavorite(),
            onDeleteClick = {},
        )
    }
}
